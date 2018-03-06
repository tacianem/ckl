package com.example.rin.cheesecakesarticles;

import java.io.Serializable;

public class Tag implements Serializable {

    private int id;
    private String label;
    private int color;

    public Tag(int id, String label, int color) {
        this.id = id;
        this.label = label;
        this.color = color;
    }

    public int getTagId() {
        return id;
    }

    public String getTagLabel() {
        return label;
    }

    public int getTagColor() {
        return color;
    }

}