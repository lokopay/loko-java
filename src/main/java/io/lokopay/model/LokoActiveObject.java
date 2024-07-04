package io.lokopay.model;

import io.lokopay.net.ResponseGetter;

public interface LokoActiveObject {

    void setResponseGetter(ResponseGetter responseGetter);

    default void trySetResponseGetter(Object object, ResponseGetter responseGetter) {
        if ( object instanceof LokoActiveObject) {
            ((LokoActiveObject) object).setResponseGetter(responseGetter);
        }
    }
}
