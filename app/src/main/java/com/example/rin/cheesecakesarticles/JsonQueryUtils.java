package com.example.rin.cheesecakesarticles;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public final class JsonQueryUtils {

    public static final String LOG_TAG = JsonQueryUtils.class.getName();

    private JsonQueryUtils() {
    }

    public static ArrayList<Article> getArticles(String json, ArrayList<Article> articles) {

        try {
            JSONArray root = new JSONArray(json);
            for (int i = 0; i < root.length(); i++) {
                JSONObject child = root.getJSONObject(i);

                String title = child.getString("title");
                String website = child.getString("website");
                String author = child.getString("authors");
                String date = child.getString("date");
                String contents = child.getString("content");
                String imageUrl = child.getString("image_url");

                JSONArray tagsRoot = child.getJSONArray("tags");
                JSONObject tagsChild = tagsRoot.getJSONObject(0);

                int id = tagsChild.getInt("id");
                String label = tagsChild.getString("label");

                ArrayList<Tag> tags = new ArrayList<>();
                int color = 0;
                switch (id) {
                    case 1:
                        color = R.color.politics;
                        break;
                    case 2:
                        color = R.color.tech;
                        break;
                    case 3:
                        color = R.color.science;
                        break;
                    case 4:
                        color = R.color.sports;
                        break;

                    default:
                        break;
                }
                Tag tag = new Tag(id, label, color);
                tags.add(tag);

                Article article = new Article(title, website, author, date, contents, tags, imageUrl);
                if (!articles.contains(article)) {
                    articles.add(article);
                }
            }

        } catch (JSONException exception) {
            Log.e(LOG_TAG, exception.getMessage(), exception);
        }

        return articles;
    }
}
