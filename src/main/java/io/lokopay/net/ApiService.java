package io.lokopay.net;

import io.lokopay.exception.LokoException;
import io.lokopay.model.LokoObjectInterface;
import lombok.AccessLevel;
import lombok.Getter;

import java.io.InputStream;
import java.lang.reflect.Type;

public abstract class ApiService {

    @Getter(AccessLevel.PROTECTED)
    private final ResponseGetter responseGetter;

    protected ApiService(ResponseGetter responseGetter) {
        this.responseGetter = responseGetter;
    }

    protected <T extends LokoObjectInterface> T request(ApiRequest request, Type typeToken)
        throws LokoException {
        return this.getResponseGetter().request(request.addUsage("loko_client"), typeToken);
    }

    protected InputStream requestStream(ApiRequest request) throws LokoException {
        return this.getResponseGetter().requestStream(request.addUsage("loko_client"));
    }

}
