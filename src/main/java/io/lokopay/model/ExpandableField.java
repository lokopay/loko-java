package io.lokopay.model;


import io.lokopay.net.ResponseGetter;
import lombok.Getter;
import lombok.Setter;

// This class is meant to be used for expandable fields in the Loko API.
// For example, ExpandableField<BalanceTransaction> in Charge.
// The class should always contain a String id, and may have a null or type T expandedObject.
// More info here: https://Loko.com/docs/api#expanding_objects
public class ExpandableField<T extends HasId> implements LokoActiveObject {
  @Setter
  @Getter
  private String id;

  private T expandedObject;

  public ExpandableField(String id, T expandedObject) {
    this.id = id;
    this.expandedObject = expandedObject;
  }

  public boolean isExpanded() {
    return expandedObject != null;
  }

    public T getExpanded() {
    return expandedObject;
  }

  public void setExpanded(T expandedObject) {
    this.expandedObject = expandedObject;
  }

  @Override
  public void setResponseGetter(ResponseGetter responseGetter) {
    trySetResponseGetter(expandedObject, responseGetter);
  }
}
