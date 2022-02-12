package com.techelevator.model;

public class LikeDTO {

    private int likeId;
    private int userId;
    private int photoId;
    private String username;
    private int numberOfLikes;
    private boolean isLiked;

    public LikeDTO() {
    }

    public LikeDTO(int likeId, int userId, int photoId, String username, boolean isLiked) {
        this.likeId = likeId;
        this.userId = userId;
        this.photoId = photoId;
        this.isLiked = isLiked;
        this.username = username;
    }

    public LikeDTO(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }
    public boolean isLiked() {
        return isLiked;
    }
    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}
