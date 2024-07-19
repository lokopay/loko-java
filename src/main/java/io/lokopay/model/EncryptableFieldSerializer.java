package io.lokopay.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.lokopay.Loko;
import io.lokopay.util.Security;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class EncryptableFieldSerializer implements JsonSerializer<Object> {

    private static String secretKey;

    public EncryptableFieldSerializer(String secretKey) {
        super();
        EncryptableFieldSerializer.secretKey = secretKey;
    }

    @Override
    public JsonElement serialize(Object src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        System.out.println("enter into EncryptableFieldSerializer");
        for (Field field : src.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            try {
                if (field.isAnnotationPresent(EncryptableField.class)) {
//                    jsonObject.addProperty("special_" + field.getName(), (String) field.get(src));
                    System.out.println("Field: " + field.getName() + " " + secretKey);
                    String encryptedData = Security.AESEncrypt((String)field.get(src), secretKey);
                    jsonObject.addProperty(field.getName(), encryptedData);
                } else {
                    jsonObject.addProperty(field.getName(), (String) field.get(src));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return jsonObject;
    }
}
