package com.example.rin.cheesecakesarticles;

import java.io.Serializable;
import java.util.ArrayList;

public class Article implements Serializable {

    private String title;
    private String website;
    private String author;
    private String date;
    private String contents;
    private ArrayList<Tag> tags;
    private String imageUrl;
    private boolean read = false;

    public Article(String title, String website, String author, String date, String contents, ArrayList<Tag> tags, String imageUrl) {
        this.title = title;
        this.website = website;
        this.author = author;
        this.date = date;
        this.contents = contents;
        this.tags = tags;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getWebsite() {
        return website;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getContents() {
        return contents;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean getRead() {
        return read;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return "\nTitle: " + title + "\nAuthor: " + author;
    }

    @Override
    public boolean equals(Object article2) {
        return (article2 instanceof Article &&
                this.author.equals(((Article) article2).getAuthor()) &&
                this.title.equals(((Article) article2).getTitle()) &&
                this.website.equals(((Article) article2).getWebsite()) &&
                this.date.equals(((Article) article2).getDate()) &&
                this.contents.equals(((Article) article2).getContents()) &&
                this.imageUrl.equals(((Article) article2).getImageUrl()));
    }
}
