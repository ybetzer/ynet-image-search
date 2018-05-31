package com.yonatanbetzer.imagesearch.adapters.viewholders;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.NetworkImageView;
import com.yonatanbetzer.imagesearch.application.ImageSearchApplication;
import com.yonatanbetzer.imagesearch.data_objects.ImageResult;
import com.yonatanbetzer.imagesearch.R;
import com.yonatanbetzer.imagesearch.utils.Constants;
import com.yonatanbetzer.imagesearch.utils.Utils;
import com.yonatanbetzer.imagesearch.server.VolleySingleton;

public class ImageResultViewHolder extends RecyclerView.ViewHolder {
    private NetworkImageView imageView;
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
                ImageSearchApplication.getAppContext().startActivity(Intent.createChooser(sendIntent, ImageSearchApplication.getAppContext().getResources().getText(R.string.share)));
            }
        });
    }

    public void bindTo(ImageResult item){
        if(imageView != null) {
            imageResult = item;
            imageView.setBackgroundColor(Utils.getRandomColor());
            if(imageResult.getPreviewHeight() > Constants.IMAGE_HEIGHT_FOR_HDPI) {
                imageView.setImageUrl(item.getPreviewURL(), VolleySingleton.getInstance().getImageLoader());
            } else {
                imageView.setImageUrl(item.getWebformatURL(), VolleySingleton.getInstance().getImageLoader());
            }

            int height = Utils.pixelsFromDP(Constants.IMAGE_HEIGHT_FOR_HDPI);
            ViewGroup.LayoutParams params = itemView.getLayoutParams();
            params.height = height;
            params.width = item.getWidthForHeight(height);
            itemView.setLayoutParams(params);
        }
    }
}
