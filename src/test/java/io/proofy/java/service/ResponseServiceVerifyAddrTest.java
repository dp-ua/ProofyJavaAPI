package io.proofy.java.service;

import com.jsunsoft.http.ResponseException;
import io.proofy.java.model.CheckMailModel;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.when;

public class ResponseServiceVerifyAddrTest extends ResponseServiceAbstractTest {
    public static final String API_KEY_CORRECT = "apiKey";
    public static final String API_KEY_WRONG = "apiKeyWrong";
    public static final String USER_ID = "userId";
    public static final String BASIC_URL = "basicUrl";
    public static final String MAIL = "mail@mail.mail";
    public static final int CORRECT_EMAIL_CID = 123;


    private void prepareMockData(String key) {

        String urlForCorrectAddrVerify = getUrlForVerifyAddr(BASIC_URL, USER_ID, API_KEY_CORRECT);
        String urlForErrorAddrVerify = getUrlForVerifyAddr(BASIC_URL, USER_ID, API_KEY_WRONG);

        when(restService.get(urlForCorrectAddrVerify)).thenReturn(getResponse(200, getCorrectContent(), urlForCorrectAddrVerify));
        when(restService.get(urlForErrorAddrVerify)).thenReturn(getResponse(200, getErrorContent(), urlForErrorAddrVerify));

        responseService = new ResponseService(restService, BASIC_URL, USER_ID, key);
    }

    @Test
    public void verifyAddrCorrectAnswer() {
        prepareMockData(API_KEY_CORRECT);
        CheckMailModel checkMailModel = responseService.verifyAddr(MAIL);

        Assert.assertEquals(CORRECT_EMAIL_CID, checkMailModel.getCid());
    }

    @Test(expected = ResponseException.class)
    public void verifyAddrAnswerWithErrorMessage() {
        prepareMockData(API_KEY_WRONG);
        CheckMailModel checkMailModel = responseService.verifyAddr(MAIL);

        // It should not come to this comparison
        Assert.assertTrue(false);
    }

    private String getCorrectContent() {
        return "{\"cid\":" + CORRECT_EMAIL_CID + "}\n";
    }

    private String getErrorContent() {
        return "{\"error\":true,\"message\":\"Parameter 'key' is not valid.\"}";
    }

    private String getUrlForVerifyAddr(String url, String user, String key) {
        return url + ResponseService.VERIFYADDR_REQUEST + "?" +
                ResponseService.UID_KEY_NAME + user + "&" +
                ResponseService.KEY_KEY_NAME + key + "&" +
                ResponseService.EMAIL_REQUEST + MAIL;
    }
}