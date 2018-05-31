package com.yonatanbetzer.imagesearch.server;

import org.json.JSONArray;

public interface AsyncHTTPJSONArrayResponseHandler {
    void onSuccess(JSONArray responseBody);

    void onFailure(String error, int errorCode);
}
