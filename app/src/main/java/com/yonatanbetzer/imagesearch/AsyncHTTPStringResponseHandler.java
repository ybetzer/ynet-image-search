package com.yonatanbetzer.imagesearch;

public interface AsyncHTTPStringResponseHandler {
    void onSuccess(String responseBody);

    void onFailure(String error, int errorCode);
}
