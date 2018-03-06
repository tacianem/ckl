package com.example.rin.cheesecakesarticles;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ArticleAdapter extends ArrayAdapter<Article> implements LoaderManager.LoaderCallbacks<Bitmap> {

    //private static final String LOG_TAG = ArticleAdapter.class.getName();
    private static final int IMAGE_LOADER_ID = 1;

    private ImageView imageUrlView;
    private String imageUrl;
    private LoaderManager loaderManager;

    public ArticleAdapter(Activity context, ArrayList<Article> articles) {
        super(context, 0, articles);
        this.loaderManager = context.getLoaderManager();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.article_item, parent, false);
        }

        final Article currentArticle = getItem(position);

        TextView titleText = listItemView.findViewById(R.id.title);
        titleText.setText(currentArticle.getTitle());

        TextView authorText = listItemView.findViewById(R.id.author);
        authorText.setText(currentArticle.getAuthor());

        if (!currentArticle.getRead()) {
            titleText.setTypeface(null, Typeface.BOLD);
            authorText.setTypeface(null, Typeface.BOLD);
        }

        imageUrlView = listItemView.findViewById(R.id.image);
        imageUrl = currentArticle.getImageUrl();
        Bitmap imageBitmap = Cache.getInstance().getLru().get(imageUrl);
        if (imageBitmap == null) {
            loaderManager.initLoader(IMAGE_LOADER_ID + position, null, this);
            //new DownloadImageTask(imageUrlView).execute(currentArticle.getImageUrl());
        } else {
            imageUrlView.setImageBitmap(imageBitmap);
        }

        TextView dateText = listItemView.findViewById(R.id.date);
        dateText.setText(currentArticle.getDate());

        return listItemView;
    }

    @Override
    public Loader<Bitmap> onCreateLoader(int i, Bundle bundle) {
        return new ArticleImageLoader(this.getContext(), imageUrl, imageUrlView);
    }

    @Override
    public void onLoadFinished(Loader<Bitmap> loader, Bitmap bitmap) {
        ((ArticleImageLoader)loader).getImageUrlView().setImageBitmap(bitmap);
        Cache.getInstance().getLru().put(((ArticleImageLoader)loader).getImageUrl(), bitmap);
    }

    @Override
    public void onLoaderReset(Loader<Bitmap> loader) {
    }


}
    /* ----------------- NETWORKING THREAD --------------------------------*/
    /*private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        private ImageView image;
        private String imageUrl;

        public DownloadImageTask(ImageView image) {
            this.image = image;
        }

        protected Bitmap doInBackground(String... urls) {
            Bitmap imageBitmap = null;
            imageUrl = urls[0];
            try {
                InputStream in = new java.net.URL(urls[0]).openStream();
                imageBitmap = BitmapFactory.decodeStream(in);
            } catch (Exception exception) {
                Log.e(LOG_TAG, exception.getMessage());
            }
            return imageBitmap;
        }

        protected void onPostExecute(Bitmap bitmap) {
            image.setImageBitmap(bitmap);
            Cache.getInstance().getLru().put(imageUrl, bitmap);
        }
    }*/


