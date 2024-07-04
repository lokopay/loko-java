package io.lokopay.net;

import io.lokopay.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;

public class LokoResponseStream extends AbstractLokoResponse<InputStream> {

    public LokoResponseStream(int code, HttpHeaders headers, InputStream body) {
        super(code, headers, body);
    }

    LokoResponse unstream() throws IOException {
        final String bodyString = StreamUtils.readToEnd(this.body, ApiResource.CHARSET);
        this.body.close();
        return new LokoResponse(this.code, this.headers, bodyString);
    }
}
