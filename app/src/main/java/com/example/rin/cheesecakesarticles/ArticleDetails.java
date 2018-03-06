package com.example.rin.cheesecakesarticles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class ArticleDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent articleDetails = getIntent();
        Article currentArticle = (Article) articleDetails.getSerializableExtra("currentArticle");

        TextView titleText = findViewById(R.id.title);
        titleText.setText(currentArticle.getTitle());

        TextView authorText = findViewById(R.id.author);
        authorText.setText(currentArticle.getAuthor());

        TextView dateText = findViewById(R.id.date);
        dateText.setText(currentArticle.getDate());

        TextView websiteText = findViewById(R.id.website);
        websiteText.setText(currentArticle.getWebsite());

        TextView labelText = findViewById(R.id.label);
        labelText.setText(currentArticle.getTags().get(0).getTagLabel());
        labelText.setBackgroundColor(getResources().getColor(currentArticle.getTags().get(0).getTagColor()));

        TextView contentsText = findViewById(R.id.contents);
        contentsText.setText(currentArticle.getContents());

        ImageView imageView = findViewById(R.id.image);
        imageView.setImageBitmap(Cache.getInstance().getLru().get(currentArticle.getImageUrl()));
    }

}


