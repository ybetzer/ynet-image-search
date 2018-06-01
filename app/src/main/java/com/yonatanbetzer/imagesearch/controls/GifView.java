package com.yonatanbetzer.imagesearch.controls;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.util.AttributeSet;
import android.view.View;

public class GifView extends View {

    private static final int DEFAULT_MOVIEW_DURATION = 1000;

    private Movie mMovie;
    private int mCurrentAnimationTime = 0;
    private int count = 0;
    private int imageResourceId = 0;

    @SuppressLint("NewApi")
    public GifView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    public void setImageResource(int mvId){
        imageResourceId = mvId;
        mMovie = Movie.decodeStream(getResources().openRawResource(mvId));
        requestLayout();
    }

    public boolean hasImageResource(){
        return imageResourceId > 0;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(mMovie != null){
            setMeasuredDimension(mMovie.width(), mMovie.height());
        }else{
            setMeasuredDimension(getSuggestedMinimumWidth(), getSuggestedMinimumHeight());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        count += 16;
        if (mMovie != null){
            int dur = mMovie.duration();
            if (dur == 0) {
                dur = DEFAULT_MOVIEW_DURATION;
            }
            mCurrentAnimationTime = count % dur;

            drawGif(canvas);
            invalidate();
        }else{
            drawGif(canvas);
        }
    }

    private void drawGif(Canvas canvas) {
        mMovie.setTime(mCurrentAnimationTime);
        mMovie.draw(canvas, 0, 0);
        canvas.restore();
    }
}
