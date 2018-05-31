package com.yonatanbetzer.imagesearch.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.yonatanbetzer.imagesearch.application.ImageSearchApplication;
import com.yonatanbetzer.imagesearch.data_objects.ImageResult;
import com.yonatanbetzer.imagesearch.R;
import com.yonatanbetzer.imagesearch.adapters.viewholders.ImageResultViewHolder;

import java.util.ArrayList;

public class ImageSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM = 1;
    private int lastPosition = -1;
    private ArrayList<ImageResult> images;
    public ImageSearchAdapter(ArrayList<ImageResult> imagesParam) {
        images = imagesParam;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;

        switch (viewType) {
            case ITEM:
                viewHolder = createItemViewHolder(parent);
                break;
            default:
                break;
        }
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder createItemViewHolder(@NonNull ViewGroup parent) {
        RecyclerView.ViewHolder viewHolder;View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_grid_item, parent, false);
        viewHolder = new ImageResultViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return ITEM;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case ITEM:
                bindItemViewHolder((ImageResultViewHolder) holder, position);
                setAnimation(holder.itemView, position);
                break;
            default:
                break;
        }
    }

    private void bindItemViewHolder(@NonNull ImageResultViewHolder holder, int position) {
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

    private void setAnimation(View viewToAnimate, int position)
    {
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(ImageSearchApplication.getAppContext(), android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}
