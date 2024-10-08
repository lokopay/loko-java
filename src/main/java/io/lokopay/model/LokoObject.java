package io.lokopay.model;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.lokopay.net.ApiResource;
import io.lokopay.net.ApiResourceDecryptor;
import io.lokopay.net.LokoResponse;
import io.lokopay.net.ResponseGetter;

public abstract class LokoObject implements LokoObjectInterface{
    public static final Gson PRETTY_PRINT_GSON =
            new GsonBuilder()
                    .setPrettyPrinting()
                    .serializeNulls()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .registerTypeAdapter(ExpandableField.class, new ExpandableFieldSerializer())
                    .create();

    private transient LokoResponse lastResponse;

    private transient JsonObject rawJsonObject;

    private static final ApiResourceDecryptor LOKO_OBJECT_DECRYPTOR = new ApiResourceDecryptor();

    @Override
    public String toString() {
        return String.format(
                "<%s@%s id=%s> JSON: %s",
                this.getClass().getName(),
                System.identityHashCode(this),
                this.getIdString(),
                PRETTY_PRINT_GSON.toJson(this));
    }

    @Override
    public LokoResponse getLastResponse() {
        return lastResponse;
    }

    @Override
    public void setLastResponse(LokoResponse response) {
        this.lastResponse = response;
    }

    /**
     * Returns the raw JsonObject exposed by the Gson library. This can be used to access properties
     * that are not directly exposed by Loko's Java library.
     *
     * <p>Note: You should always prefer using the standard property accessors whenever possible.
     * Because this method exposes Gson's underlying API, it is not considered fully stable. Loko's
     * Java library might move off Gson in the future and this method would be removed or change
     * significantly.
     *
     * @return The raw JsonObject.
     */
    public JsonObject getRawJsonObject() {
        // Lazily initialize this the first time the getter is called.
        if ((this.rawJsonObject == null) && (this.getLastResponse() != null)) {
            this.rawJsonObject =
                    ApiResource.INTERNAL_GSON.fromJson(
                            (String) this.getLastResponse().body(),
                            JsonObject.class
                    );
        }

        return this.rawJsonObject;
    }

    public String toJson() {
        return PRETTY_PRINT_GSON.toJson(this);
    }

    private Object getIdString() {
        try {
            Field idField = this.getClass().getDeclaredField("id");
            return idField.get(this);
        } catch (SecurityException e) {
            return "";
        } catch (NoSuchFieldException e) {
            return "";
        } catch (IllegalArgumentException e) {
            return "";
        } catch (IllegalAccessException e) {
            return "";
        }
    }

    protected static boolean equals(Object a, Object b) {
        return a == null ? b == null : a.equals(b);
    }

    /**
     * Deserialize JSON into super class {@code LokoObject} where the underlying concrete class
     * corresponds to type specified in root-level {@code object} field of the JSON input.
     *
     * <p>Note that the expected JSON input is data at the {@code object} value, as a sibling to
     * {@code previousAttributes}, and not the discriminator field containing a string.
     *
     * @return JSON data to be deserialized to super class {@code LokoObject}
     */
    static LokoObject deserializeLokoObject(
            JsonObject eventDataObjectJson, ResponseGetter responseGetter) {
        String type = eventDataObjectJson.getAsJsonObject().get("object").getAsString();
        Class<? extends LokoObject> cl = EventDataClassLookup.classLookup.get(type);
        LokoObject object =
                ApiResource.deserializeLokoObject(
                        eventDataObjectJson, cl != null ? cl : LokoRawJsonObject.class, responseGetter);
        return object;
    }

    public static LokoObject deserializeLokoObject(
            JsonObject payload, Type type, ResponseGetter responseGetter) {
        LokoObject object = ApiResource.INTERNAL_GSON.fromJson(payload, type);

        if (object instanceof LokoActiveObject) {
            ((LokoActiveObject) object).setResponseGetter(responseGetter);
        }

        return object;
    }

    @SuppressWarnings("unchecked")
    public static <T> T deserializeLokoObject(
            String payload, Class<T> type, ResponseGetter responseGetter) {
        return (T) deserializeLokoObject(payload, (Type) type, responseGetter);
    }

    public static LokoObject deserializeLokoObject(
            String payload, Type type, ResponseGetter responseGetter) {

        LokoObject object = ApiResource.INTERNAL_GSON.fromJson(payload, type);

        if (object instanceof LokoActiveObject) {
            ((LokoActiveObject) object).setResponseGetter(responseGetter);
        }

        return object;
    }

    public static <T> T decryptLokoObject(T resource, String key) {
        return (T) LOKO_OBJECT_DECRYPTOR.decrypt((LokoObject) resource, key);
    }
}
