package com.yonatanbetzer.imagesearch.server;

public interface AsyncHTTPStringResponseHandler {
    void onSuccess(String responseBody);

    void onFailure(String error, int errorCode);
}
