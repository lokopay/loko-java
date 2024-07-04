package io.lokopay.model;

import io.lokopay.exception.LokoException;
import io.lokopay.net.RequestOptions;

import java.util.Map;

/** Common interface for Stripe objects that can store metadata. */
public interface MetadataStore<T> {
  Map<String, String> getMetadata();

  MetadataStore<T> update(Map<String, Object> params) throws LokoException;

  MetadataStore<T> update(Map<String, Object> params, RequestOptions options)
      throws LokoException;
}
