package com.yonatanbetzer.imagesearch;

import com.yonatanbetzer.imagesearch.adapters.ImageSearchAdapter;
import com.yonatanbetzer.imagesearch.data_objects.ImageResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import java.util.ArrayList;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class ImageSearchAdapterTest {
    ImageSearchAdapter adapter;
    ArrayList<ImageResult> images = new ArrayList<>();

    @Before public void setup (){
        adapter = new ImageSearchAdapter(images);
    }

    @Test
    public void itemCountShouldBeZero() {
        images.clear();
        assertEquals("item count should be zero", 0, adapter.getItemCount());
    }

    @Test
    public void afterAddingOneItemCountShouldBeOne() {
        ImageResult image = new ImageResult();
        images.add(image);
        assertEquals("item count should be 1", 1, adapter.getItemCount());
    }

    @Test
    public void getItemViewTypeShouldReturnOne () {
        assertEquals("Item type should always be 1", adapter.getItemViewType(0), 1);
        assertEquals("Item type should always be 1", adapter.getItemViewType(1), 1);
        assertEquals("Item type should always be 1", adapter.getItemViewType(100), 1);
        assertEquals("Item type should always be 1", adapter.getItemViewType(1000000), 1);
        assertEquals("Item type should always be 1", adapter.getItemViewType(-9), 1);
        assertEquals("Item type should always be 1", adapter.getItemViewType(Integer.MAX_VALUE), 1);
    }
}
