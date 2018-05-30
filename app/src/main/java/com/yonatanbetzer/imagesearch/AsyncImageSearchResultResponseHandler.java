package com.yonatanbetzer.imagesearch;

import java.util.ArrayList;

public interface AsyncImageSearchResultResponseHandler {
    void onSuccess(ArrayList<ImageResult> responseBody);
    void onFailure(String error, int errorCode);
}
