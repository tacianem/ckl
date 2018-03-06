package com.example.rin.cheesecakesarticles;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class Cache {

    private static Cache cacheInstance;
    private LruCache<String, Bitmap> lru;

    private Cache() {
        lru = new LruCache<>(1024);
    }

    public static Cache getInstance() {
        if (cacheInstance == null) {
            cacheInstance = new Cache();
        }
        return cacheInstance;
    }

    public LruCache<String, Bitmap> getLru() {
        return lru;
    }
}


