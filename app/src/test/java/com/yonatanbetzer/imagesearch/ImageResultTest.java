package com.yonatanbetzer.imagesearch;

import com.yonatanbetzer.imagesearch.data_objects.ImageResult;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class ImageResultTest {
    @Test
    public void nullSourceGetsNullImageResult() {
        ImageResult result = ImageResult.fromJsonObject(null);
        assertNull(result);
    }

    @Test
    public void nullArraySourceGetsEmptyImageResultArrray() {
        ArrayList<ImageResult> result = ImageResult.fromJsonArray(null);
        assertNotNull(result);
        assertEquals(result.size(), 0);
    }

    @Test
    public void fromJsonObjectCreatesCorrectObject() {
        String sourceString = "{\n" +
                "      \"largeImageURL\": \"https://pixabay.com/get/eb3cb5072cf4053ed1584d05fb1d409ee273e4d419ac104497f7c679a4e4b7be_1280.jpg\",\n" +
                "      \"webformatHeight\": 433,\n" +
                "      \"webformatWidth\": 640,\n" +
                "      \"likes\": 447,\n" +
                "      \"imageWidth\": 4056,\n" +
                "      \"id\": 2948404,\n" +
                "      \"user_id\": 3558510,\n" +
                "      \"views\": 174860,\n" +
                "      \"comments\": 46,\n" +
                "      \"pageURL\": \"https://pixabay.com/en/kitty-cat-kid-cat-domestic-cat-2948404/\",\n" +
                "      \"imageHeight\": 2746,\n" +
                "      \"webformatURL\": \"https://pixabay.com/get/eb3cb5072cf4053ed1584d05fb1d409ee273e4d419ac104497f7c679a4e4b7be_640.jpg\",\n" +
                "      \"type\": \"photo\",\n" +
                "      \"previewHeight\": 101,\n" +
                "      \"tags\": \"kitty, cat, kid cat\",\n" +
                "      \"downloads\": 116837,\n" +
                "      \"user\": \"ilyessuti\",\n" +
                "      \"favorites\": 322,\n" +
                "      \"imageSize\": 3464997,\n" +
                "      \"previewWidth\": 150,\n" +
                "      \"userImageURL\": \"https://cdn.pixabay.com/user/2017/12/05/15-36-30-145_250x250.jpg\",\n" +
                "      \"previewURL\": \"https://cdn.pixabay.com/photo/2017/11/14/13/06/kitty-2948404_150.jpg\"\n" +
                "    }";
        try {
            JSONObject source = new JSONObject(sourceString);
            ImageResult result = ImageResult.fromJsonObject(source);
            assertNotNull(result);
            assertEquals("id mismatch", result.getId(), 2948404);
            assertEquals("page URL mismatch", result.getPageURL(), "https://pixabay.com/en/kitty-cat-kid-cat-domestic-cat-2948404/");
            assertEquals("webformat image url mismatch", result.getWebformatURL(), "https://pixabay.com/get/eb3cb5072cf4053ed1584d05fb1d409ee273e4d419ac104497f7c679a4e4b7be_640.jpg");
            assertEquals("preview image url mismatch", result.getPreviewURL(), "https://cdn.pixabay.com/photo/2017/11/14/13/06/kitty-2948404_150.jpg");
            assertEquals("height mismatch", result.getWebformatHeight(), 433);
            assertEquals("width mismatch", result.getWebformatWidth(), 640);
        } catch (JSONException e) {
            assertTrue("Mock source was not correctly formatted JSON", false);
        }
    }
}
