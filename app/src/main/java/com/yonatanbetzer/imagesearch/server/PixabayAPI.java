package com.yonatanbetzer.imagesearch.server;

import com.yonatanbetzer.imagesearch.data_objects.ImageResult;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class PixabayAPI {
    private static final String API_KEY = "6814610-cd083c066ad38bb511337fb2b";

    public static void search(String query, int page, int resultsPerPage, final AsyncImageSearchResultResponseHandler listenr) {
        try {
            query = URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        VolleySingleton.getInstance().getJSONObjectAsync(
                    "https://pixabay.com/api/?q=" + query +
                        "&key=" + API_KEY +
                        "&page=" + page +
                        "&per_page=" + resultsPerPage,
                new AsyncHTTPJSONResponseHandler() {
            @Override
            public void onSuccess(JSONObject responseBody) {
                JSONArray hits = responseBody.optJSONArray("hits");
                if(hits != null) {
                    ArrayList<ImageResult> results = ImageResult.fromJsonArray(hits);
                    if(listenr != null) {
                        listenr.onSuccess(results);
                    }
                } else {
                    if(listenr != null) {
                        listenr.onFailure("Pixabay API Returned null result", 0);
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
