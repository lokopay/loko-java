package io.lokopay.net;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
class BaseApiRequest {
    private final BaseAddress baseAddress;
    private final ApiResource.RequestMethod method;
    private final String path;
    private final RequestOptions options;
    private final List<String> usage;
}
