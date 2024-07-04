package io.lokopay.net;

public class LokoResponse extends AbstractLokoResponse {
    public LokoResponse(int code, HttpHeaders headers, String body) {
        super(code, headers, body);
    }
}
