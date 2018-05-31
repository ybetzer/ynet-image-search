package com.yonatanbetzer.imagesearch.data_objects;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ImageResult {
    private String largeImageURL;
    private int webformatHeight;
    private int webformatWidth;
    private int likes;
    private int imageWidth;
    private int id;
    private int user_id;
    private int views;
    private int comments;
    private String pageURL;
    private int imageHeight;
    private String webformatURL;
    private String type;
    private int previewHeight;
    private String tags;
    private int downloads;
    private String user;
    private int favorites;
    private int imageSize;
    private int previewWidth;
    private String userImageURL;
    private String previewURL;

    public static ArrayList<ImageResult> fromJsonArray(JSONArray source){
        if(source == null) {
            return new ArrayList<>();
        }
        ArrayList<ImageResult> results = new ArrayList<>();
        for (int i = 0; i < source.length(); i++) {
            ImageResult item = ImageResult.fromJsonObject(source.optJSONObject(i));
            results.add(item);
        }
        return results;
    }

    public static ImageResult fromJsonObject(JSONObject source){
        ImageResult result = new ImageResult();
        if(source == null) {
            return null;
        }
        result.largeImageURL = source.optString("largeImageURL", "");
        result.webformatHeight = source.optInt("webformatHeight", 0);
        result.webformatWidth = source.optInt("webformatWidth", 0);
        result.likes = source.optInt("likes", 0);
        result.imageWidth = source.optInt("imageWidth", 0);
        result.id = source.optInt("id", 0);
        result.user_id = source.optInt("user_id", 0);
        result.views = source.optInt("views", 0);
        result.comments = source.optInt("comments", 0);
        result.pageURL = source.optString("pageURL", "");
        result.imageHeight = source.optInt("imageHeight", 0);
        result.webformatURL = source.optString("webformatURL", "");
        result.type = source.optString("type", "");
        result.previewHeight = source.optInt("previewHeight", 0);
        result.tags = source.optString("tags", "");
        result.downloads = source.optInt("downloads", 0);
        result.user = source.optString("user", "");
        result.favorites = source.optInt("favorites", 0);
        result.imageSize = source.optInt("imageSize", 0);
        result.previewWidth = source.optInt("previewWidth", 0);
        result.userImageURL = source.optString("userImageURL", "");
        result.previewURL = source.optString("previewURL", "");

        return result;
    }

    public int getWidthForHeight(int height) {
        //
        //  webformatWidth         newWidth
        //  --------------    =   ----------
        //  webformatHeight         height

        if(this.webformatHeight > 0) {
            return (int)Math.floor(((float)this.webformatWidth / (float)this.webformatHeight) * (float)height);
        }
        return 0;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public int getWebformatHeight() {
        return webformatHeight;
    }

    public int getWebformatWidth() {
        return webformatWidth;
    }

    public int getLikes() {
        return likes;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getViews() {
        return views;
    }

    public int getComments() {
        return comments;
    }

    public String getPageURL() {
        return pageURL;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public String getWebformatURL() {
        return webformatURL;
    }

    public String getType() {
        return type;
    }

    public int getPreviewHeight() {
        return previewHeight;
    }

    public String getTags() {
        return tags;
    }

    public int getDownloads() {
        return downloads;
    }

    public String getUser() {
        return user;
    }

    public int getFavorites() {
        return favorites;
    }

    public int getImageSize() {
        return imageSize;
    }

    public int getPreviewWidth() {
        return previewWidth;
    }

    public String getUserImageURL() {
        return userImageURL;
    }

    public String getPreviewURL() {
        return previewURL;
    }
}
