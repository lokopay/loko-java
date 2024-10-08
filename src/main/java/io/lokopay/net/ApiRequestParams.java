package io.lokopay.net;
import java.util.Map;

/**
 * Super class to all api request params objects. It exposes a convenient method converting the
 * typed parameter into the legacy support of untyped {@code Map<String, Object>} param.
 */
public abstract class ApiRequestParams {
  /**
   * Param key for an `extraParams` map. Any param/sub-param specifying a field intended to support
   * extra params from users should have the annotation
   * {@code @SerializedName(ApiRequestParams.EXTRA_PARAMS_KEY)}. Logic to handle this is in {@link
   * ApiRequestParamsConverter}.
   */
  public static final String EXTRA_PARAMS_KEY = "_loko_java_extra_param_key";

  /** Converter mapping typed API request parameters into an untyped map. */
  private static final ApiRequestParamsConverter PARAMS_CONVERTER = new ApiRequestParamsConverter();

  private static final ApiRequestParamsEncryptor PARAMS_ENCRYPTOR = new ApiRequestParamsEncryptor();
  /**
   * Interface implemented by all enum parameter to get the actual string value that Loko API
   * expects. Internally, it used in custom serialization {@link ApiRequestParamsConverter}
   * converting empty string enum to null.
   */
  public interface EnumParam {
    String getValue();
  }

  /**
   * Convert `this` api request params to an untyped map. The conversion is specific to api request
   * params object. Please see documentation in {@link
   * ApiRequestParamsConverter#convert(ApiRequestParams)}.
   */
  public Map<String, Object> toMap() {
    return PARAMS_CONVERTER.convert(this);
  }

  public ApiRequestParams encryptParams(String key) {
    PARAMS_ENCRYPTOR.encrypt(this, key);
    return this;
  }

  /**
   * Convert `params` api request params to an untyped map. The conversion is specific to api
   * request params object. Please see documentation in {@link
   * ApiRequestParamsConverter#convert(ApiRequestParams)}.
   */
  public static Map<String, Object> paramsToMap(ApiRequestParams params) {
    if (params == null) {
      return null;
    }
    return params.toMap();
  }
}
