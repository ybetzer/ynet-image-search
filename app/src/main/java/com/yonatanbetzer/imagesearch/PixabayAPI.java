package com.yonatanbetzer.imagesearch;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class PixabayAPI {
    private static final String API_KEY = "6814610-cd083c066ad38bb511337fb2b";

    public static void search(String query, final AsyncImageSearchResultResponseHandler listenr) {
        VolleySingleton.getInstance().getJSONObjectAsync(
                "https://pixabay.com/api/?q=" + query + "&key=" + API_KEY,
                new AsyncHTTPJSONResponseHandler() {
            @Override
            public void onSuccess(JSONObject responseBody) {
                JSONArray hits = responseBody.optJSONArray("hits");
                if(hits != null) {
                    ArrayList<ImageResult> results = ImageResult.fromJsonArray(hits);
                    if(listenr != null) {
                        listenr.onSuccess(results);
                    }
                }
            }

            @Override
            public void onFailure(String error, int errorCode) {
                if(listenr != null) {
                    listenr.onFailure(error, errorCode);
                }
            }
        });
    }
}
