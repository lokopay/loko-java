package io.lokopay.net;

import io.lokopay.exception.LokoException;
import io.lokopay.model.LokoObjectInterface;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Map;

public interface ResponseGetter {


    @SuppressWarnings("TypeParameterUnusedInFormals")
    @Deprecated
    <T extends LokoObjectInterface> T request(
        BaseAddress baseAddress,
        ApiResource.RequestMethod method,
        String path,
        Map<String, Object> params,
        Type typeToken,
        RequestOptions options
    ) throws LokoException;

    @SuppressWarnings("TypeParameterUnusedInFormals")
    default <T extends LokoObjectInterface> T request(ApiRequest request, Type typeToken)
            throws LokoException {
        return request(
                request.getBaseAddress(),
                request.getMethod(),
                request.getPath(),
                request.getParams(),
                typeToken,
                request.getOptions()
        );
//                request.getOptions(),
//                request.getApiMode());
    };

    @SuppressWarnings("TypeParameterUnusedInFormals")
    @Deprecated
    InputStream requestStream(
            BaseAddress baseAddress,
            ApiResource.RequestMethod method,
            String path,
            Map<String, Object> params,
            RequestOptions options
    ) throws LokoException;

    default InputStream requestStream(ApiRequest request) throws LokoException {
        return requestStream(
                request.getBaseAddress(),
                request.getMethod(),
                request.getPath(),
                request.getParams(),
                request.getOptions());
    };

    default void validateRequestOptions(RequestOptions options) {}
}
