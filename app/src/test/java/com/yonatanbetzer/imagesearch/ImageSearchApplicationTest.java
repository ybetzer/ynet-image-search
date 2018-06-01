package com.yonatanbetzer.imagesearch;

import com.yonatanbetzer.imagesearch.application.ImageSearchApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import java.util.ArrayList;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class ImageSearchApplicationTest {
    @Test
    public void applicationCreated() {
        assertNotNull("Application is null", ImageSearchApplication.getInstance());
    }

    @Test
    public void validContext() {
        assertNotNull("Application context is null", ImageSearchApplication.getAppContext());
    }
}
