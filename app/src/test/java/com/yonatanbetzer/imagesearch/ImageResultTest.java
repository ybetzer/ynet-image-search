package com.yonatanbetzer.imagesearch;

import com.yonatanbetzer.imagesearch.data_objects.ImageResult;
import org.json.JSONArray;
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

    @Test
    public void fromJsonArrayCreatesCorrectObject() {
        String sourceString = "[\n" +
                "    {\n" +
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
                "    },\n" +
                "    {\n" +
                "      \"largeImageURL\": \"https://pixabay.com/get/ec30b00a2df01c22d2524518b74a4e97e771e3d504b0144392f7c17aaeefb3_1280.jpg\",\n" +
                "      \"webformatHeight\": 640,\n" +
                "      \"webformatWidth\": 613,\n" +
                "      \"likes\": 353,\n" +
                "      \"imageWidth\": 2392,\n" +
                "      \"id\": 551554,\n" +
                "      \"user_id\": 617282,\n" +
                "      \"views\": 79778,\n" +
                "      \"comments\": 33,\n" +
                "      \"pageURL\": \"https://pixabay.com/en/kitty-cat-kitten-pet-animal-cute-feline--551554/\",\n" +
                "      \"imageHeight\": 2500,\n" +
                "      \"webformatURL\": \"https://pixabay.com/get/ec30b00a2df01c22d2524518b74a4e97e771e3d504b0144392f7c17aaeefb3_640.jpg\",\n" +
                "      \"type\": \"photo\",\n" +
                "      \"previewHeight\": 150,\n" +
                "      \"tags\": \"kitty cat kitten pet animal cute feline domestic young fur adorable paw playful friendship cat cat kitten kitten kitten kitten kitten\",\n" +
                "      \"downloads\": 24625,\n" +
                "      \"user\": \"Ty_Swartz\",\n" +
                "      \"favorites\": 294,\n" +
                "      \"imageSize\": 945751,\n" +
                "      \"previewWidth\": 144,\n" +
                "      \"userImageURL\": \"https://cdn.pixabay.com/user/2014/11/30/13-45-12-52_250x250.jpg\",\n" +
                "      \"previewURL\": \"https://cdn.pixabay.com/photo/2014/11/30/14/11/kitty-551554_150.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"largeImageURL\": \"https://pixabay.com/get/e833b5082ff3043ed1584d05fb1d409ee273e4d419ac104497f7c679a4e4b7be_1280.jpg\",\n" +
                "      \"webformatHeight\": 426,\n" +
                "      \"webformatWidth\": 640,\n" +
                "      \"likes\": 234,\n" +
                "      \"imageWidth\": 4800,\n" +
                "      \"id\": 1647775,\n" +
                "      \"user_id\": 778429,\n" +
                "      \"views\": 55390,\n" +
                "      \"comments\": 21,\n" +
                "      \"pageURL\": \"https://pixabay.com/en/cat-kitten-tree-green-summer-animal-dome-1647775/\",\n" +
                "      \"imageHeight\": 3200,\n" +
                "      \"webformatURL\": \"https://pixabay.com/get/e833b5082ff3043ed1584d05fb1d409ee273e4d419ac104497f7c679a4e4b7be_640.jpg\",\n" +
                "      \"type\": \"photo\",\n" +
                "      \"previewHeight\": 99,\n" +
                "      \"tags\": \"cat kitten tree green summer animal domestic adorable nice sweet cat cat cat cat cat kitten kitten kitten kitten\",\n" +
                "      \"downloads\": 35663,\n" +
                "      \"user\": \"Kessa\",\n" +
                "      \"favorites\": 231,\n" +
                "      \"imageSize\": 2329880,\n" +
                "      \"previewWidth\": 150,\n" +
                "      \"userImageURL\": \"\",\n" +
                "      \"previewURL\": \"https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_150.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"largeImageURL\": \"https://pixabay.com/get/ea36b80b28f41c22d2524518b74a4e97e771e3d504b0144392f7c17aaeefb3_1280.jpg\",\n" +
                "      \"webformatHeight\": 426,\n" +
                "      \"webformatWidth\": 640,\n" +
                "      \"likes\": 281,\n" +
                "      \"imageWidth\": 4272,\n" +
                "      \"id\": 339400,\n" +
                "      \"user_id\": 246570,\n" +
                "      \"views\": 70010,\n" +
                "      \"comments\": 33,\n" +
                "      \"pageURL\": \"https://pixabay.com/en/animal-cat-kitten-funny-yawning-cat-cat--339400/\",\n" +
                "      \"imageHeight\": 2848,\n" +
                "      \"webformatURL\": \"https://pixabay.com/get/ea36b80b28f41c22d2524518b74a4e97e771e3d504b0144392f7c17aaeefb3_640.jpg\",\n" +
                "      \"type\": \"photo\",\n" +
                "      \"previewHeight\": 99,\n" +
                "      \"tags\": \"animal cat kitten funny yawning cat cat cat cat cat kitten funny\",\n" +
                "      \"downloads\": 23173,\n" +
                "      \"user\": \"wilkernet\",\n" +
                "      \"favorites\": 247,\n" +
                "      \"imageSize\": 1383246,\n" +
                "      \"previewWidth\": 150,\n" +
                "      \"userImageURL\": \"https://cdn.pixabay.com/user/2014/05/07/06-49-11-983_250x250.jpg\",\n" +
                "      \"previewURL\": \"https://cdn.pixabay.com/photo/2014/05/07/06/44/animal-339400_150.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"largeImageURL\": \"https://pixabay.com/get/e034b50e29f41c22d2524518b74a4e97e771e3d504b0144392f7c17aaeefb3_1280.jpg\",\n" +
                "      \"webformatHeight\": 426,\n" +
                "      \"webformatWidth\": 640,\n" +
                "      \"likes\": 230,\n" +
                "      \"imageWidth\": 4000,\n" +
                "      \"id\": 914110,\n" +
                "      \"user_id\": 1336974,\n" +
                "      \"views\": 41456,\n" +
                "      \"comments\": 16,\n" +
                "      \"pageURL\": \"https://pixabay.com/en/cat-kitten-rozko%C5%A1n%C3%A9-little-wood-cat-cat--914110/\",\n" +
                "      \"imageHeight\": 2666,\n" +
                "      \"webformatURL\": \"https://pixabay.com/get/e034b50e29f41c22d2524518b74a4e97e771e3d504b0144392f7c17aaeefb3_640.jpg\",\n" +
                "      \"type\": \"photo\",\n" +
                "      \"previewHeight\": 99,\n" +
                "      \"tags\": \"cat kitten rozkošné little wood cat cat cat cat cat kitten kitten\",\n" +
                "      \"downloads\": 23842,\n" +
                "      \"user\": \"petkation\",\n" +
                "      \"favorites\": 206,\n" +
                "      \"imageSize\": 2628645,\n" +
                "      \"previewWidth\": 150,\n" +
                "      \"userImageURL\": \"https://cdn.pixabay.com/user/2016/10/04/11-56-00-152_250x250.jpg\",\n" +
                "      \"previewURL\": \"https://cdn.pixabay.com/photo/2015/08/30/10/58/cat-914110_150.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"largeImageURL\": \"https://pixabay.com/get/e834b80d28f6073ed1584d05fb1d409ee273e4d419ac104497f7c679a4e4b7be_1280.jpg\",\n" +
                "      \"webformatHeight\": 480,\n" +
                "      \"webformatWidth\": 640,\n" +
                "      \"likes\": 234,\n" +
                "      \"imageWidth\": 2560,\n" +
                "      \"id\": 1192026,\n" +
                "      \"user_id\": 1892688,\n" +
                "      \"views\": 64888,\n" +
                "      \"comments\": 23,\n" +
                "      \"pageURL\": \"https://pixabay.com/en/cat-pet-striped-kitten-young-white-backg-1192026/\",\n" +
                "      \"imageHeight\": 1920,\n" +
                "      \"webformatURL\": \"https://pixabay.com/get/e834b80d28f6073ed1584d05fb1d409ee273e4d419ac104497f7c679a4e4b7be_640.jpg\",\n" +
                "      \"type\": \"photo\",\n" +
                "      \"previewHeight\": 112,\n" +
                "      \"tags\": \"cat pet striped kitten young white background cat cat cat cat cat\",\n" +
                "      \"downloads\": 28599,\n" +
                "      \"user\": \"Chiemsee2016\",\n" +
                "      \"favorites\": 253,\n" +
                "      \"imageSize\": 909777,\n" +
                "      \"previewWidth\": 150,\n" +
                "      \"userImageURL\": \"\",\n" +
                "      \"previewURL\": \"https://cdn.pixabay.com/photo/2016/02/10/16/37/cat-1192026_150.jpg\"\n" +
                "    }]";
        try {
            JSONArray source = new JSONArray(sourceString);
            ArrayList<ImageResult> resultArrayList = ImageResult.fromJsonArray(source);
            assertNotNull(resultArrayList);
            assertEquals("result has 6 items", 6, resultArrayList.size());

            ImageResult result = resultArrayList.get(0);
            assertNotNull(result);
            assertEquals("id mismatch", result.getId(), 2948404);
            assertEquals("page URL mismatch", result.getPageURL(), "https://pixabay.com/en/kitty-cat-kid-cat-domestic-cat-2948404/");
            assertEquals("webformat image url mismatch", result.getWebformatURL(), "https://pixabay.com/get/eb3cb5072cf4053ed1584d05fb1d409ee273e4d419ac104497f7c679a4e4b7be_640.jpg");
            assertEquals("preview image url mismatch", result.getPreviewURL(), "https://cdn.pixabay.com/photo/2017/11/14/13/06/kitty-2948404_150.jpg");
            assertEquals("height mismatch", result.getWebformatHeight(), 433);
            assertEquals("width mismatch", result.getWebformatWidth(), 640);
        } catch (JSONException e) {
            assertTrue("malformed mock JSON array", false);
        }
    }

        @Test
    public void correctWidthForHeight() {
        ImageResult mockImageResult = new ImageResult();
        mockImageResult.setWebformatHeight(100);
        mockImageResult.setWebformatWidth(200);

        assertEquals(2, mockImageResult.getWidthForHeight(1));
    }

    @Test
    public void correctWidthForHeightWithDivideByZero() {
        ImageResult mockImageResult = new ImageResult();
        mockImageResult.setWebformatHeight(0);
        mockImageResult.setWebformatWidth(200);

        assertEquals(0, mockImageResult.getWidthForHeight(1));
    }

    @Test
    public void correctWidthForHeightWithNoValuesSet() {
        ImageResult mockImageResult = new ImageResult();

        assertEquals(0, mockImageResult.getWidthForHeight(1));
    }
}
