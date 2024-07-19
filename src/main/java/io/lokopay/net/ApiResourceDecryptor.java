package io.lokopay.net;

import io.lokopay.model.EncryptableField;
import io.lokopay.model.LokoObject;
import io.lokopay.util.Security;

import java.lang.reflect.Field;

public class ApiResourceDecryptor {

    public LokoObject decrypt(LokoObject lokoObject, String key) {

        if (lokoObject == null) {
            return null;
        }

        try {
            for (Field field : lokoObject.getClass().getDeclaredFields()) {
                if (ApiResource.class.isAssignableFrom(field.getType())) {
                    field.setAccessible(true);

                    LokoObject subObject = (LokoObject) field.get(lokoObject);
                    field.set(lokoObject, decrypt(subObject, key));
                }

                if (field.isAnnotationPresent(EncryptableField.class)) {
                    field.setAccessible(true);

                    String encryptedText = field.get(lokoObject).toString();
                    String decryptedText = "";
                    if (encryptedText != null && !encryptedText.isEmpty()) {
                        decryptedText = Security.AESDecrypt(encryptedText, key);
                    }

                    field.set(lokoObject, decryptedText);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lokoObject;
    }
}
