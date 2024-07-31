package io.lokopay.net;

import io.lokopay.exception.SignatureVerificationException;
import io.lokopay.model.Event;
import io.lokopay.util.Security;
import io.lokopay.util.StringUtils;
import io.lokopay.model.LokoObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public final class Webhook {
  private static final long DEFAULT_TOLERANCE = 300;


  public static Event constructLokoEvent(String payload) {
    return LokoObject.deserializeLokoObject(
                    payload, Event.class, ApiResource.getGlobalResponseGetter());
  }
  /**
   * Returns an Event instance using the provided JSON payload. Throws a JsonSyntaxException if the
   * payload is not valid JSON, and a SignatureVerificationException if the signature verification
   * fails for any reason.
   *
   * @param payload the payload sent by Loko.
   * @param sigHeader the contents of the signature header sent by Loko.
   * @param secret secret used to generate the signature.
   * @return the Event instance
   * @throws SignatureVerificationException if the verification fails.
   */
  public static Event constructEvent(String requestUrl, String payload, String sigHeader, String secretKey)
      throws SignatureVerificationException {
    return constructEvent(requestUrl, payload, sigHeader, secretKey, DEFAULT_TOLERANCE);
  }

//  /**
//   * Returns an Event instance using the provided JSON payload. Throws a JsonSyntaxException if the
//   * payload is not valid JSON, and a SignatureVerificationException if the signature verification
//   * fails for any reason.
//   *
//   * @param payload the payload sent by Loko.
//   * @param sigHeader the contents of the signature header sent by Loko.
//   * @param secret secret used to generate the signature.
//   * @param tolerance maximum difference in seconds allowed between the header's timestamp and the
//   *     current time
//   * @return the Event instance
//   * @throws SignatureVerificationException if the verification fails.
//   */
//  public static Event constructEvent(
//      String payload, String sigHeader, String secret, long tolerance)
//      throws SignatureVerificationException {
//    return constructEvent(payload, sigHeader, secret, tolerance);
//  }

  /**
   * Returns an Event instance using the provided JSON payload. Throws a JsonSyntaxException if the
   * payload is not valid JSON, and a SignatureVerificationException if the signature verification
   * fails for any reason.
   *
   * @param payload the payload sent by Loko.
   * @param sigHeader the contents of the signature header sent by Loko.
   * @param secret secret used to generate the signature.
   * @param tolerance maximum difference in seconds allowed between the header's timestamp and the
   *     current time
   * @param clock instance of clock if you want to use custom time instance
   * @return the Event instance
   * @throws SignatureVerificationException if the verification fails.
   */
  public static Event constructEvent(
      String requestUrl, String payload, String sigHeader, String secretKey, long tolerance)
      throws SignatureVerificationException {
    Event event =
        LokoObject.deserializeLokoObject(
            payload, Event.class, ApiResource.getGlobalResponseGetter());

    Signature.verifyHeader(requestUrl, payload, sigHeader, secretKey, tolerance);

    event.setSecretKey(secretKey);

    return event;
  }



  public static final class Signature {

    public static final String EXPECTED_SCHEME = "v1";

    public static final String SPLITTER = ";";
    /**
     * Verifies the signature header sent by Loko. Throws a SignatureVerificationException if the
     * verification fails for any reason.
     *
     * @param payload the payload sent by Loko.
     * @param sigHeader the contents of the signature header sent by Loko.
     * @param secret secret used to generate the signature.
     * @param tolerance maximum difference allowed between the header's timestamp and the current
     *     time
     * @throws SignatureVerificationException if the verification fails.
     */
//    public static boolean verifyHeader(
//        String requestUrl, String payload, String sigHeader, String secret, long tolerance)
//        throws SignatureVerificationException {
//
//      return verifyHeader(url, payload, sigHeader, secret, tolerance);
//    }

    /**
     * Verifies the signature header sent by Loko. Throws a SignatureVerificationException if the
     * verification fails for any reason.
     *
     * @param payload the payload sent by Loko.
     * @param sigHeader the contents of the signature header sent by Loko.
     * @param secret secret used to generate the signature.
     * @param tolerance maximum difference allowed between the header's timestamp and the current
     *     time
     * @param clock instance of clock if you want to use custom time instance
     * @throws SignatureVerificationException if the verification fails.
     */
    public static boolean verifyHeader(
        String url, String payload, String sigHeader, String secret, long tolerance)
        throws SignatureVerificationException {

      // Get timestamp and signatures from header
      long timestamp = getTimestamp(sigHeader);
      String nonce = getNonce(sigHeader);
      String signature = getSignatures(sigHeader);

      if (timestamp <= 0) {
        throw new SignatureVerificationException(
            "Unable to extract timestamp and signatures from header", sigHeader);
      }
      if (signature == null || signature.isEmpty()) {
        throw new SignatureVerificationException(
            "No signatures found with expected scheme", sigHeader);
      }

      // Compute expected signature
      String signedPayload = url + payload + nonce + timestamp; //String.format("%s%s%s%d", url, payload, nonce, timestamp);
      String expectedSignature;
      try {
        expectedSignature = computeSignature(signedPayload, secret);
      } catch (Exception e) {
        throw new SignatureVerificationException(
            "Unable to compute signature for payload", sigHeader);
      }

      // Check if expected signature is found in list of header's signatures
      boolean signatureFound = StringUtils.secureCompare(expectedSignature, signature);

      if (!signatureFound) {
        throw new SignatureVerificationException(
            "No signatures found matching the expected signature for payload", sigHeader);
      }

      long currentTime = Util.getTimeNow() / 1000;

      // Check tolerance
      if ((tolerance > 0) && (timestamp < (currentTime - tolerance))) {
        throw new SignatureVerificationException("Timestamp outside the tolerance zone", sigHeader);
      }

      return true;
    }

    /**
     * Extracts the timestamp in a signature header.
     *
     * @param sigHeader the signature header
     * @return the timestamp contained in the header.
     */
    private static long getTimestamp(String sigHeader) {
      String[] items = sigHeader.split(SPLITTER, -1);

      for (String item : items) {
        String[] itemParts = item.split("=", 2);
        if (itemParts[0].equals("t")) {
          return Long.parseLong(itemParts[1]);
        }
      }

      return -1;
    }

    private static String getNonce(String sigHeader) {
      String[] items = sigHeader.split(SPLITTER, -1);

      for (String item : items) {
        String[] itemParts = item.split("=", 2);
        if (itemParts[0].equals("n")) {
          return itemParts[1];
        }
      }

      return null;
    }

    /**
     * Extracts the signatures matching a given scheme in a signature header.
     *
     * @param sigHeader the signature header
     * @param scheme the signature scheme to look for.
     * @return the list of signatures matching the provided scheme.
     */
    private static String getSignatures(String sigHeader) {
      String[] items = sigHeader.split(SPLITTER, -1);

      for (String item : items) {
        String[] itemParts = item.split("=", 2);
        if (itemParts[0].equals("s")) {
          return itemParts[1];
        }
      }

      return null;
    }

    /**
     * Computes the signature for a given payload and secret.
     *
     * <p>The current scheme used by Loko ("v1") is HMAC/SHA-256.
     *
     * @param payload the payload to sign.
     * @param secret the secret used to generate the signature.
     * @return the signature as a string.
     */
    private static String computeSignature(String payload, String secret)
        throws Exception {
//      return Util.computeHmacSha256(secret, payload);

      return Security.HmacSignature(payload, secret);
    }
  }

  public static final class Util {
//    /**
//     * Computes the HMAC/SHA-256 code for a given key and message.
//     *
//     * @param key the key used to generate the code.
//     * @param message the message.
//     * @return the code as a string.
//     */
//    public static String computeHmacSha256(String key, String message)
//        throws NoSuchAlgorithmException, InvalidKeyException {
//
//      System.out.println("data: " + message);
//      Mac mac = Mac.getInstance("HmacSHA256");
//      SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA256");
//      mac.init(secretKeySpec);
//
//      byte[] hmacBytes = mac.doFinal(message.getBytes());
////      hasher.init(new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
////      byte[] hash = hasher.doFinal(message.getBytes(StandardCharsets.UTF_8));
////      String result = "";
////      for (byte b : hash) {
////        result += Integer.toString((b & 0xff) + 0x100, 16).substring(1);
////      }
//      return Base64.getEncoder().encodeToString(hmacBytes);
//    }

    /**
     * Returns the current UTC timestamp in seconds.
     *
     * @return the timestamp as a long.
     */
    public static long getTimeNow() {
      long time = System.currentTimeMillis() / 1000L;
      return time;
    }
  }
}
