package com.NewsTok.User.Models;

import jakarta.persistence.*;



public class Reels {

    private Long reelsId;

    private Long newsId;
    private String background_color;
    private String font_color;
    private String font_family;

    private String image;

    private String music;

    private String summary;

    private String title;

    public Long getReelsId() {
        return reelsId;
    }

    public void setReelsId(Long reelsId) {
        this.reelsId = reelsId;
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public String getBackground_color() {
        return background_color;
    }

    public void setBackground_color(String background_color) {
        this.background_color = background_color;
    }

    public String getFont_color() {
        return font_color;
    }

    public void setFont_color(String font_color) {
        this.font_color = font_color;
    }

    public String getFont_family() {
        return font_family;
    }

    public void setFont_family(String font_family) {
        this.font_family = font_family;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
