package io.lokopay.net;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class JsonEncoder {


    public static HttpContent createHttpContent(Map<String, Object> params) throws IOException {
        return HttpContent.buildJsonContent(Objects.requireNonNullElseGet(params, HashMap::new));

    }

    public static String createJsonString(Map<String, Object> params) throws IOException {

        Gson gson = new Gson();
        return gson.toJson(params);
    }
}
