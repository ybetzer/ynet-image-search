package com.yonatanbetzer.imagesearch;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.yonatanbetzer.imagesearch.activity.ImageSearchActivity;
import com.yonatanbetzer.imagesearch.controls.GifView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class ImageSearchActivityTest {

    ImageSearchActivity activity;
    SearchView searchView;
    ProgressBar progressBar;
    GifView noResultsGif;
    @Before
    public void setup() {
        activity = Robolectric.setupActivity(ImageSearchActivity.class);
        searchView = activity.findViewById(R.id.search);
        progressBar = activity.findViewById(R.id.progress_bar);
        noResultsGif = activity.findViewById(R.id.no_results);
    }

    @Test
    public void activityInNotNull() {
        assertNotNull("ImageSearchActivity is null", activity);
    }

    @Test
    public void searchViewInNotNull() {
        assertNotNull("search view is null", searchView);
    }

    @Test
    public void progressBarIsNotVisible() {
        assertEquals("progressBar is visible when activity starts", progressBar.getVisibility(), View.GONE);
    }


    @Test
    public void noResultsGifLoadedResource() {
        assertTrue("no results gif has no resource drawable", noResultsGif.hasImageResource());
    }
}
