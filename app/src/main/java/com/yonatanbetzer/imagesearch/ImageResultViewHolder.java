package com.yonatanbetzer.imagesearch;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.toolbox.NetworkImageView;

public class ImageResultViewHolder extends RecyclerView.ViewHolder {
    private NetworkImageView imageView;

    public ImageResultViewHolder(View itemView) {
        super(itemView);
        this.imageView = itemView.findViewById(R.id.image_view);
    }

    public void bindTo(ImageResult item){
        if(imageView != null) {
            imageView.setImageUrl(item.getImageUrl(), VolleySingleton.getInstance().getImageLoader());
        }
    }
}
