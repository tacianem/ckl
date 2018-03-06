package com.example.rin.cheesecakesarticles;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ArticlesActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    public static final String JSON_URL = "https://cheesecakelabs.com/challenge/";
    private static final int ARTICLE_LOADER_ID = 0;

    private static ArrayList<Article> articles = new ArrayList<>();

    private TextView emptyListView;
    private ListView articlesListView;
    private String sortOption = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_activity);

        emptyListView = findViewById(R.id.empty_view);
        articlesListView = findViewById(R.id.article_list);

        ConnectivityManager connectivityManager =
                (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        if (activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(ARTICLE_LOADER_ID, null, this);
        } else {
            ProgressBar progressBar = findViewById(R.id.indeterminateBar);
            progressBar.setVisibility(View.GONE);
            emptyListView.setText(R.string.no_connection);
            articlesListView.setEmptyView(emptyListView);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dots_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.sort:
                Intent sortIntent = new Intent(ArticlesActivity.this, ArticleSorting.class);
                startActivityForResult(sortIntent, 0);
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                sortOption = intent.getStringExtra("sortOption");
            }
        }
    }

    @Override
    public Loader<String> onCreateLoader(int i, Bundle bundle) {
        return new JsonLoader(ArticlesActivity.this, JSON_URL);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String json) {
        ProgressBar progressBar = findViewById(R.id.indeterminateBar);

        if (json.isEmpty()) {
            progressBar.setVisibility(View.GONE);
            emptyListView.setText(R.string.empty_list);
            articlesListView.setEmptyView(emptyListView);
        } else {
            articles = JsonQueryUtils.getArticles(json, articles);

            if (!articles.isEmpty()) {
                if (sortOption != "") {
                    switch (sortOption) {
                        case "Author":
                            Collections.sort(articles, authorSorting);
                            break;
                        case "Title":
                            Collections.sort(articles, titleSorting);
                            break;
                        case "Date":
                            Collections.sort(articles, dateSorting);
                            break;
                        case "Website":
                            Collections.sort(articles, websiteSorting);
                            break;
                        case "Article Tag":
                            Collections.sort(articles, tagSorting);
                            break;
                        case "Article Contents":
                            Collections.sort(articles, contentsSorting);
                            break;
                        default:
                            break;
                    }
                }

                final ArticleAdapter articleAdapter = new ArticleAdapter(this, articles);
                articlesListView.setAdapter(articleAdapter);

                progressBar.setVisibility(View.GONE);

                articlesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Article currentArticle = articleAdapter.getItem(position);
                        if (!currentArticle.getRead()) {
                            currentArticle.setRead(true);
                            markViewAsRead(view);
                        }

                        displayArticle(currentArticle);
                    }
                });
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
    }

    public static Comparator<Article> authorSorting = new Comparator<Article>() {
        @Override
        public int compare(Article article1, Article article2) {
            return article1.getAuthor().compareToIgnoreCase(article2.getAuthor());
        }
    };

    public static Comparator<Article> titleSorting = new Comparator<Article>() {
        @Override
        public int compare(Article article1, Article article2) {
            return article1.getTitle().compareToIgnoreCase(article2.getTitle());
        }
    };

    public static Comparator<Article> dateSorting = new Comparator<Article>() {
        @Override
        public int compare(Article article1, Article article2) {
            return -article1.getDate().compareToIgnoreCase(article2.getDate());
        }
    };

    public static Comparator<Article> websiteSorting = new Comparator<Article>() {
        @Override
        public int compare(Article article1, Article article2) {
            return article1.getWebsite().compareToIgnoreCase(article2.getWebsite());
        }
    };

    public static Comparator<Article> tagSorting = new Comparator<Article>() {
        @Override
        public int compare(Article article1, Article article2) {
            return article1.getTags().get(0).getTagLabel().compareToIgnoreCase(article2.getTags().get(0).getTagLabel());
        }
    };

    public static Comparator<Article> contentsSorting = new Comparator<Article>() {
        @Override
        public int compare(Article article1, Article article2) {
            return article1.getContents().compareToIgnoreCase(article2.getContents());
        }
    };

    private void markViewAsRead(View view) {
        TextView titleText = view.findViewById(R.id.title);
        titleText.setTypeface(null, Typeface.NORMAL);

        TextView authorText = view.findViewById(R.id.author);
        authorText.setTypeface(null, Typeface.NORMAL);
    }

    private void displayArticle(Article currentArticle) {
        Intent detailsIntent = new Intent(ArticlesActivity.this, ArticleDetails.class);
        detailsIntent.putExtra("currentArticle", currentArticle);
        startActivity(detailsIntent);
    }

}
