package io.lokopay.model;

import io.lokopay.net.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class PagingIterator<T extends HasId> extends ApiResource implements Iterator<T> {
  private final String url;

  private final Type pageType;

  private LokoCollectionInterface<T> currentCollection;
  private Iterator<T> currentDataIterator;

  private String lastId;

  PagingIterator(
      final LokoCollectionInterface<T> lokoCollection,
      ResponseGetter responseGetter,
      Type pageType) {
    this.url = lokoCollection.getUrl();

    this.pageType = pageType;

    this.currentCollection = lokoCollection;
    this.currentDataIterator = lokoCollection.getData().iterator();
    setResponseGetter(responseGetter);
  }

  @Override
  public boolean hasNext() {
    return currentDataIterator.hasNext() || currentCollection.getHasMore();
  }

  @Override
  public T next() {
    // if we've run out of data on the current page, try to fetch another
    // one
    if (!currentDataIterator.hasNext() && currentCollection.getHasMore()) {
      try {
        Map<String, Object> params = new HashMap<>();

        // copy all the parameters from the initial request
        Map<String, Object> initialParams = currentCollection.getRequestParams();
        if (initialParams != null) {
          params.putAll(initialParams);
        }

        // then put our new page start in
        params.put("starting_after", lastId);

        this.currentCollection = list(params, currentCollection.getRequestOptions());

        this.currentDataIterator = currentCollection.getData().iterator();
      } catch (final Exception e) {
        throw new RuntimeException("Unable to lazy-load loko objects", e);
      }
    }

    if (currentDataIterator.hasNext()) {
      final T next = currentDataIterator.next();
      this.lastId = next.getId();
      return next;
    }

    throw new NoSuchElementException();
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }

  private LokoCollectionInterface<T> list(
      final Map<String, Object> params, final RequestOptions options) throws Exception {
    ApiRequest request =
        new ApiRequest(BaseAddress.API, RequestMethod.GET, url, params, options);
    return getResponseGetter().request(request, pageType);
  }
}
