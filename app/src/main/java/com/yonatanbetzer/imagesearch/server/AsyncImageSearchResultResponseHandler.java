package com.yonatanbetzer.imagesearch.server;

import com.yonatanbetzer.imagesearch.data_objects.ImageResult;

import java.util.ArrayList;

public interface AsyncImageSearchResultResponseHandler {
    void onSuccess(ArrayList<ImageResult> responseBody);
    void onFailure(String error, int errorCode);
}
