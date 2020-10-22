package io.proofy.java.service;

import com.jsunsoft.http.FilterSupport;
import com.jsunsoft.http.OtherwiseSupport;
import com.jsunsoft.http.ResponseHandler;
import lombok.SneakyThrows;
import org.apache.http.StatusLine;
import org.apache.http.entity.ContentType;
import org.junit.Assert;
import org.junit.Before;
import org.mockito.Mock;

import java.net.URI;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static org.mockito.Mockito.mock;

public abstract class ResponseServiceAbstractTest {
    public static final String NOT_USED_PART_MESSAGE = "Not Used part";

    @Mock
    protected RestService restService;

    protected ResponseService responseService;

    @Before
    public void setUp() throws Exception {
        restService = mock(RestService.class);
    }

    protected ResponseHandler<String> getResponse(int statusCode, String content, String uri) {
        return new ResponseHandler<String>() {
            @Override
            public boolean hasContent() {
                return content != null;
            }

            @Override
            public int getStatusCode() {
                return statusCode;
            }

            @Override
            public String orElse(String defaultValue) {
                Assert.assertTrue(NOT_USED_PART_MESSAGE, false);
                return null;
            }

            @Override
            public String orElseThrow(String defaultValue) {
                Assert.assertTrue(NOT_USED_PART_MESSAGE, false);
                return null;
            }

            @Override
            public <X extends Throwable> String orThrow(Function<ResponseHandler<? super String>, X> exceptionFunction) throws X {
                Assert.assertTrue(NOT_USED_PART_MESSAGE, false);
                return null;
            }

            @Override
            public <X extends Throwable> String orThrow(String defaultValue, Function<ResponseHandler<? super String>, X> exceptionFunction) throws X {
                Assert.assertTrue(NOT_USED_PART_MESSAGE, false);
                return null;
            }

            @Override
            public <X extends Throwable> String getOrThrow(Supplier<X> exceptionSupplier) throws X {
                Assert.assertTrue(NOT_USED_PART_MESSAGE, false);
                return null;
            }

            @Override
            public <X extends Throwable> String getOrThrow(String defaultValue, Supplier<X> exceptionSupplier) throws X {
                Assert.assertTrue(NOT_USED_PART_MESSAGE, false);
                return null;
            }

            @Override
            public String orElseThrow() {
                Assert.assertTrue(NOT_USED_PART_MESSAGE, false);
                return null;
            }

            @Override
            public String get() {
                return content;
            }

            @Override
            public String getErrorText() {
                Assert.assertTrue("Need add assert", false);
                return null;
            }

            @SneakyThrows
            @Override
            public URI getURI() {
                return new URI(uri);
            }

            @Override
            public StatusLine getStatusLine() {
                Assert.assertTrue(NOT_USED_PART_MESSAGE, false);
                return null;
            }

            @Override
            public ContentType getContentType() {
                Assert.assertTrue(NOT_USED_PART_MESSAGE, false);
                return null;
            }

            @Override
            public boolean isSuccess() {
                Assert.assertTrue(NOT_USED_PART_MESSAGE, false);
                return false;
            }

            @Override
            public void ifHasContent(Consumer<? super String> consumer) {
                Assert.assertTrue(NOT_USED_PART_MESSAGE, false);
            }

            @Override
            public OtherwiseSupport<String> ifSuccess(Consumer<ResponseHandler<String>> consumer) {
                Assert.assertTrue(NOT_USED_PART_MESSAGE, false);
                return null;
            }

            @Override
            public void ifNotSuccess(Consumer<ResponseHandler<String>> consumer) {
                Assert.assertTrue(NOT_USED_PART_MESSAGE, false);
            }

            @Override
            public FilterSupport<String> filter(Predicate<ResponseHandler<String>> predicate) {
                Assert.assertTrue(NOT_USED_PART_MESSAGE, false);
                return null;
            }
        };
    }
}