package com.techelevator.model;

public class FavoriteDTO {

    private int favoritedId;
    private int userId;
    private int photoId;
    private String username;

    public FavoriteDTO(int favoritedId, int userId, int photoId, String username) {
        this.favoritedId = favoritedId;
        this.userId = userId;
        this.photoId = photoId;
        this.username = username;
    }
    public FavoriteDTO(){}

    public int getFavoritedId() {
        return favoritedId;
    }

    public int getUserId() {
        return userId;
    }

    public int getPhotoId() {
        return photoId;
    }

    public String getUsername() {
        return username;
    }

    public void setFavoritedId(int favoriteId) {
        this.favoritedId = favoritedId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
