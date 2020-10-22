package io.proofy.java.service.impl;

import com.jsunsoft.http.*;
import io.proofy.java.service.RestService;
import org.apache.http.entity.ContentType;

public class RestImpl implements RestService {
    @Override
    public ResponseHandler<String> get(String uri) {
        return getP(uri, ContentType.DEFAULT_TEXT);
    }

    @Override
    public ResponseHandler<String> get(String uri, ContentType contentType) {
        return getP(uri, contentType);
    }

    private HttpRequest getHttpRequest(ContentType contentType) {
        return HttpRequestBuilder.create(ClientBuilder.create().build())
                .addContentType(contentType)
                .build();
    }

    private ResponseHandler<String> getP(String uri, ContentType contentType) {
        return getHttpRequest(contentType).target(uri).get("", new TypeReference<String>() {
        });
    }
}