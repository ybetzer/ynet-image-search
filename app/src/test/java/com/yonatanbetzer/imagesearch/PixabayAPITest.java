package com.yonatanbetzer.imagesearch;

import com.yonatanbetzer.imagesearch.data_objects.ImageResult;
import com.yonatanbetzer.imagesearch.server.AsyncImageSearchResultResponseHandler;
import com.yonatanbetzer.imagesearch.server.PixabayAPI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import java.util.ArrayList;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class PixabayAPITest {
    @Test
    public void nullSourceGetsNullImageResult() {
        PixabayAPI.search(null, 1, 1, new AsyncImageSearchResultResponseHandler() {
            @Override
            public void onSuccess(ArrayList<ImageResult> responseBody) {
                assertTrue("On success called on invalid parameters", false);
            }

            @Override
            public void onFailure(String error, int errorCode) {
                assertNotNull(error);
            }
        });
    }
}
