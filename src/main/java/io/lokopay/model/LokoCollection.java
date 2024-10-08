package io.lokopay.model;

import io.lokopay.net.RequestOptions;
import io.lokopay.net.ResponseGetter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Provides a representation of a single page worth of data from the Loko API.
 *
 * <p>The following code will have the effect of iterating through a single page worth of invoice
 * data retrieve from the API:
 *
 * <p>
 *
 * <pre>{@code
 * foreach (Invoice invoice : Invoice.list(...).getData()) {
 *   System.out.println("Current invoice = " + invoice.toString());
 * }
 * }</pre>
 *
 * <p>The class also provides a helper for iterating over collections that may be longer than a
 * single page:
 *
 * <p>
 *
 * <pre>{@code
 * foreach (Invoice invoice : Invoice.list(...).autoPagingIterable()) {
 *   System.out.println("Current invoice = " + invoice.toString());
 * }
 * }</pre>
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class LokoCollection<T extends HasId> extends LokoObject
    implements LokoCollectionInterface<T>, LokoActiveObject {
  private transient ResponseGetter responseGetter;
  String object;

  @Getter(onMethod_ = {@Override})
  List<T> data;

  @Getter(onMethod_ = {@Override})
  Boolean hasMore;

  @Getter(onMethod_ = {@Override})
  String url;

  @Getter(onMethod_ = {@Override})
  @Setter(onMethod = @__({@Override}))
  private transient RequestOptions requestOptions;

  @Getter(onMethod_ = {@Override})
  @Setter(onMethod = @__({@Override}))
  private Map<String, Object> requestParams;

  @Setter(onMethod = @__({@Override}))
  private transient Type pageTypeToken;

  public Iterable<T> autoPagingIterable() {
    this.responseGetter.validateRequestOptions(this.requestOptions);
    return new PagingIterable<>(this, responseGetter, pageTypeToken);
  }

  public Iterable<T> autoPagingIterable(Map<String, Object> params) {
    this.responseGetter.validateRequestOptions(this.requestOptions);

    this.setRequestParams(params);
    return new PagingIterable<>(this, responseGetter, pageTypeToken);
  }

  /**
   * Constructs an iterable that can be used to iterate across all objects across all pages. As page
   * boundaries are encountered, the next page will be fetched automatically for continued
   * iteration.
   *
   * @param params request parameters (will override the parameters from the initial list request)
   * @param options request options (will override the options from the initial list request)
   */
  public Iterable<T> autoPagingIterable(Map<String, Object> params, RequestOptions options) {
    this.responseGetter.validateRequestOptions(options);
    this.setRequestOptions(options);
    this.setRequestParams(params);
    return new PagingIterable<>(this, responseGetter, pageTypeToken);
  }

  @Override
  public void setResponseGetter(ResponseGetter responseGetter) {
    this.responseGetter = responseGetter;
    if (this.data != null) {
      for (T item : data) {
        trySetResponseGetter(item, responseGetter);
      }
    }
  }
}
