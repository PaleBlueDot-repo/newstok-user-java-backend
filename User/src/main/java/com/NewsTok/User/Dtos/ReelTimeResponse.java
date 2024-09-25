package com.NewsTok.User.Dtos;

public class ReelTimeResponse {
    private Long reelsId;
    private Long userId;
    private int timeSpent;



    // Getters and Setters

    public Long getReelsId() {
        return reelsId;
    }

    public void setReelsId(Long reelsId) {
        this.reelsId = reelsId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(int timeSpent) {
        this.timeSpent = timeSpent;
    }
}
