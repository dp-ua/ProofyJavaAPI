package io.proofy.java.service;

import com.jsunsoft.http.ResponseHandler;
import org.apache.http.entity.ContentType;

public interface RestService {
    ResponseHandler<String> get(String uri);

    ResponseHandler<String> get(String uri, ContentType contentType);
}
