package io.lokopay.model;

import io.lokopay.net.LokoResponse;

public interface  LokoObjectInterface {
    public LokoResponse getLastResponse();

    public void setLastResponse(LokoResponse response);
}
