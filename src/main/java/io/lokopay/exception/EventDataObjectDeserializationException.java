package io.lokopay.exception;

import lombok.Getter;

public class EventDataObjectDeserializationException extends LokoException {
  private static final long serialVersionUID = 2L;

  /**
   * JSON intended as event data object {@link EventDataObjectDeserializer#getObject()} that fails
   * deserialization.
   */
  @Getter private final String rawJson;

  public EventDataObjectDeserializationException(String message, String rawJson) {
    super(message, null, null, null);
    this.rawJson = rawJson;
  }
}
