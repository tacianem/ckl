package com.example.rin.cheesecakesarticles;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

public class ArticleImageLoader extends AsyncTaskLoader<Bitmap> {

    private String imageUrl;
    private ImageView imageUrlView;

    private static final String LOG_TAG = ArticleImageLoader.class.getName();

    public ArticleImageLoader(Context context, String url, ImageView imageUrlView) {
        super(context);
        this.imageUrl = url;
        this.imageUrlView = imageUrlView;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public Bitmap loadInBackground() {
        Bitmap imageBitmap = null;
        try {
            InputStream in = new java.net.URL(imageUrl).openStream();
            imageBitmap = BitmapFactory.decodeStream(in);
        } catch (Exception exception) {
            Log.e(LOG_TAG, exception.getMessage());
        }
        return imageBitmap;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ImageView getImageUrlView() {
        return imageUrlView;
    }
}

