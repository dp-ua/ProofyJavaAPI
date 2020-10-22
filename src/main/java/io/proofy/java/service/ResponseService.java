package io.proofy.java.service;

import com.jsunsoft.http.ResponseException;
import com.jsunsoft.http.ResponseHandler;
import io.proofy.java.model.CheckMailModel;

import javax.json.JsonObject;

import static io.proofy.java.service.JsonService.JSON_SERVICE;

public class ResponseService {
    public static final String ERROR_KEY = "error";
    public static final String UID_KEY_NAME = "aid=";
    public static final String KEY_KEY_NAME = "key=";
    public static final String EMAIL_REQUEST = "email=";
    public static final String VERIFYADDR_REQUEST = "verifyaddr";

    private final RestService restService;
    private final String basicUri;
    private final String userId;
    private final String apiKey;

    public ResponseService(RestService restService, String basicUri, String userId, String apiKey) {
        this.restService = restService;
        this.basicUri = basicUri;
        this.userId = userId;
        this.apiKey = apiKey;
    }

    public CheckMailModel verifyAddr(String email) throws ResponseException {
        ResponseHandler<String> response = restService.get(getMailCheckUri(email));
        JsonObject contentJson = getValidatedContentJsonObject(response);
        return JSON_SERVICE.getCheckMailModelFromJsonObject(contentJson);
    }

    private JsonObject getValidatedContentJsonObject(ResponseHandler<String> response) throws ResponseException {
        checkResponse(response);
        return JSON_SERVICE.getJsonObjectFromString(response.get());
    }

    private void checkResponse(ResponseHandler<String> response) throws ResponseException {
        if (response.getStatusCode() == 200 && response.hasContent()) {
            checkContentInResponse(response);
            return;
        }
        throw new ResponseException(response.getStatusCode(), response.getErrorText(), response.getURI());
    }

    private void checkContentInResponse(ResponseHandler<String> response) throws ResponseException {
        JsonObject jsonObject = JSON_SERVICE.getJsonObjectFromString(response.get());
        if (jsonObject.containsKey(ERROR_KEY)) {
            throw new ResponseException(200, jsonObject.getString("message"), response.getURI());
        }
    }

    private String getMailCheckUri(String email) {
        return getBasicUriWithAuth(VERIFYADDR_REQUEST)
                + "&" + EMAIL_REQUEST + email;
    }

    private String getBasicUriWithAuth(String requestType) {
        return basicUri + requestType + "?" + getAuthString();
    }

    private String getAuthString() {
        return UID_KEY_NAME + userId
                + "&"
                + KEY_KEY_NAME + apiKey;
    }
}
