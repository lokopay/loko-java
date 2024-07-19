package io.lokopay.net;

import io.lokopay.model.EncryptableField;
import io.lokopay.util.Security;

import java.lang.reflect.Field;

class ApiRequestParamsEncryptor {


    ApiRequestParams encrypt(ApiRequestParams params, String key) {
        if (params == null) {
            return null;
        }

        try {
            for (Field field : params.getClass().getDeclaredFields()) {
                if (ApiRequestParams.class.isAssignableFrom(field.getType())) {
                    field.setAccessible(true);

                    ApiRequestParams subParams = (ApiRequestParams) field.get(params);
                    field.set(params, this.encrypt(subParams, key));
                }

                if (field.isAnnotationPresent(EncryptableField.class)) {
                    field.setAccessible(true);

                    String plainText = field.get(params).toString();
                    String encryptedData = Security.AESEncrypt(plainText, key);
                    field.set(params, encryptedData);
                }
            }
        } catch (Exception e) {

            throw new RuntimeException(e);
        }

        return params;
    }
}
