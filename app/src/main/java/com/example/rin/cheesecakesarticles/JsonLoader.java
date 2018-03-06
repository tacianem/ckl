package com.example.rin.cheesecakesarticles;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonLoader extends AsyncTaskLoader<String> {

    private String jsonUrl;
    private static final String LOG_TAG = JsonLoader.class.getName();

    public JsonLoader(Context context, String jsonUrl) {
        super(context);
        this.jsonUrl = jsonUrl;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        if (jsonUrl == null) {
            return null;
        }

        StringBuilder json = new StringBuilder();
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(jsonUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream input = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }

        } catch (Exception exception) {
            Log.e(LOG_TAG, exception.getMessage());

        } finally {
            urlConnection.disconnect();
        }

        return json.toString();
    }

}
