package io.lokopay.model;
import io.lokopay.net.RequestOptions;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public interface LokoCollectionInterface<T> extends LokoObjectInterface{

    List<T> getData();

    Boolean getHasMore();

    String getUrl();

    RequestOptions getRequestOptions();

    Map<String, Object> getRequestParams();

    void setRequestOptions(RequestOptions requestOptions);

    void setRequestParams(Map<String, Object> requestParams);

    void setPageTypeToken(Type type);
}
