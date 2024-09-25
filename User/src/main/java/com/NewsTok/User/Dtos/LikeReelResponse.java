package com.NewsTok.User.Dtos;

public class LikeReelResponse {
    private Long reelsId;
    private Long userId;
    private boolean liked;
    private int likeCount;

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

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }


    // Getters and Setters
}
