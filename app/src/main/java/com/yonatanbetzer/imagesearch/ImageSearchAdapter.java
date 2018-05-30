package com.yonatanbetzer.imagesearch;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public class ImageSearchAdapter extends RecyclerView.Adapter<ImageResultViewHolder> {
    private ArrayList<ImageResult> images;

    public ImageSearchAdapter(ArrayList<ImageResult> imagesParam) {
        images = imagesParam;
    }

    @NonNull
    @Override
    public ImageResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_grid_item, parent, false);
        return new ImageResultViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageResultViewHolder holder, int position) {
        if(images != null && images.size() > position) {
            ImageResult imageResult = images.get(position);
            holder.bindTo(imageResult);
        }
    }

    @Override
    public int getItemCount() {
        if(images == null) {
            return 0;
        }
        return images.size();
    }



}
