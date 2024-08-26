package com.NewsTok.User.Models;

public class FinalRecommendationResponse {
    private News news;

    private Reels reels;

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public Reels getReels() {
        return reels;
    }

    public void setReels(Reels reels) {
        this.reels = reels;
    }
}
