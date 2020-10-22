package io.proofy.java.service;

import io.proofy.java.model.CheckMailModel;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.ByteArrayInputStream;

import static io.proofy.java.model.CheckMailModel.CID_KEY_NAME;

public enum JsonService {
    JSON_SERVICE;

    public JsonObject getJsonObjectFromString(String msg) {
        JsonReader jsonReader = Json.createReader(new ByteArrayInputStream(msg.getBytes()));
        return jsonReader.readObject();
    }

    public CheckMailModel getCheckMailModelFromJsonObject(JsonObject jsonObject) throws ClassCastException{
        CheckMailModel checkMailModel = new CheckMailModel();
        if (jsonObject.containsKey(CID_KEY_NAME)) {
            checkMailModel.setCid(jsonObject.getInt(CID_KEY_NAME));
        } else {
            throw new ClassCastException("Can't cast JsonObject to CheckMailModel");
        }
        return checkMailModel;
    }
}
