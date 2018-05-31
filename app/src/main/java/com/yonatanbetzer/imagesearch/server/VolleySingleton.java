package com.yonatanbetzer.imagesearch.server;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.yonatanbetzer.imagesearch.application.ImageSearchApplication;

import org.json.JSONObject;

import java.util.HashMap;

public class VolleySingleton {
    private static VolleySingleton mInstance = null;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private HashMap<String, JSONObject> cache_json_object_responses = new HashMap<>();

    private VolleySingleton(){
        mRequestQueue = Volley.newRequestQueue(ImageSearchApplication.getAppContext());

        mImageLoader = new ImageLoader(this.mRequestQueue, new ImageLoader.ImageCache() {
            ActivityManager am = (ActivityManager) ImageSearchApplication.getAppContext().getSystemService(Context.ACTIVITY_SERVICE);
            int memClassBytes = am.getMemoryClass() * 1024 * 1024;
            int cacheSize = memClassBytes / 3;
            LruCache<String, Bitmap> mCache = new LruCache<>(cacheSize);
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
        });
    }

    public static VolleySingleton getInstance(){
        if(mInstance == null){
            mInstance = new VolleySingleton();
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        return this.mRequestQueue;
    }

    public ImageLoader getImageLoader(){
        return this.mImageLoader;
    }

    public void getJSONObjectAsync(final String url,
                                   final AsyncHTTPJSONResponseHandler handler) {

        if (cache_json_object_responses.get(url) != null) {
            handler.onSuccess(cache_json_object_responses.get(url));
        } else {

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            if(handler != null) {
                                cache_json_object_responses.put(url, response);
                                handler.onSuccess(response);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if(handler != null) {
                                handler.onFailure(error.getLocalizedMessage(), 0);
                            }

                        }
                    });

            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    2500,
                    3,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setShouldCache(true);
            getRequestQueue().add(jsonObjectRequest);
        }
    }
}