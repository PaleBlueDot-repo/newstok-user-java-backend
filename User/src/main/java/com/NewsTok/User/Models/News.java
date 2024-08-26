package com.NewsTok.User.Models;

import jakarta.persistence.*;

public class News {

    private Long id;
    private String newspaperName;
    private String category;


    private String title;


    private String article;


    private String link;

    private String published;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNewspaperName() {
        return newspaperName;
    }

    public void setNewspaperName(String newspaperName) {
        this.newspaperName = newspaperName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }
}
