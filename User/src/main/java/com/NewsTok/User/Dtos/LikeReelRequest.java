package com.NewsTok.User.Dtos;

public class LikeReelRequest {
    private Long reelsId;
    private Long userId;

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
}
