package io.lokopay.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class LokoRawJsonObjectDeserializer implements JsonDeserializer<LokoRawJsonObject> {
  /** Deserializes a JSON payload into a {@link LokoRawJsonObject} object. */
  @Override
  public LokoRawJsonObject deserialize(
      JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    LokoRawJsonObject object = new LokoRawJsonObject();
    object.json = json.getAsJsonObject();
    return object;
  }
}
