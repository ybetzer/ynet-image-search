package com.yonatanbetzer.imagesearch.adapters.viewholders;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.Glide;
import com.yonatanbetzer.imagesearch.application.ImageSearchApplication;
import com.yonatanbetzer.imagesearch.data_objects.ImageResult;
import com.yonatanbetzer.imagesearch.R;
import com.yonatanbetzer.imagesearch.utils.Constants;
import com.yonatanbetzer.imagesearch.utils.Utils;
import com.yonatanbetzer.imagesearch.server.VolleySingleton;

public class ImageResultViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private ImageResult imageResult;

    public ImageResultViewHolder(View itemView) {
        super(itemView);
        this.imageView = itemView.findViewById(R.id.image_view);

        View shareButton = itemView.findViewById(R.id.share_button);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, imageResult.getPageURL());
                sendIntent.setType("text/plain");
                ImageSearchApplication.getCurrentActivity().startActivity(Intent.createChooser(sendIntent, ImageSearchApplication.getAppContext().getResources().getText(R.string.share)));
            }
        });
    }

    public void bindTo(ImageResult item){
        if(imageView != null) {
            imageResult = item;
            imageView.setBackgroundColor(Utils.getRandomColor());

            if(imageResult.getPreviewHeight() > Constants.IMAGE_HEIGHT) {
                Glide.with(ImageSearchApplication.getAppContext())
                        .load(item.getPreviewURL())
                        .into(imageView);
            } else {
                Glide.with(ImageSearchApplication.getAppContext())
                        .load(item.getWebformatURL())
                        .into(imageView);
            }

            int height = Utils.pixelsFromDP(Constants.IMAGE_HEIGHT);
            ViewGroup.LayoutParams params = itemView.getLayoutParams();
            params.height = height;
            params.width = item.getWidthForHeight(height);
            itemView.setLayoutParams(params);
        }
    }
}
