package com.example.rin.cheesecakesarticles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ArticleSorting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_sorting);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClickSort(View view) {
        Intent sortingReturn = new Intent(ArticleSorting.this, ArticlesActivity.class);
        sortingReturn.putExtra("sortOption", ((TextView) view).getText());
        setResult(Activity.RESULT_OK, sortingReturn);
        finish();
    }

}
