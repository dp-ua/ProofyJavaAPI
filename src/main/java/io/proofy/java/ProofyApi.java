package io.proofy.java;

import com.jsunsoft.http.ResponseException;
import io.proofy.java.model.CheckMailModel;
import io.proofy.java.service.ResponseService;
import io.proofy.java.service.impl.RestImpl;
import lombok.NonNull;

public class ProofyApi {
    private final ResponseService responseService;

    private final String basicUri = "https://api.proofy.io/";

    public ProofyApi(@NonNull String userId, @NonNull String apiKey) {
        responseService = new ResponseService(new RestImpl(), basicUri, userId, apiKey);
    }

    public CheckMailModel emailCheck(@NonNull String email) throws ResponseException, ClassCastException {
        return responseService.verifyAddr(email);
    }
}
