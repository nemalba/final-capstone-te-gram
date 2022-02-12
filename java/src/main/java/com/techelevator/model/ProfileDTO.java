package com.techelevator.model;

public class ProfileDTO {
    private int userId;
    private String bio;
    private String profilePicUrl;
    private String username;

    public ProfileDTO(int userId, String bio, String profilePicUrl,String username) {
        this.userId = userId;
        this.bio = bio;
        this.profilePicUrl = profilePicUrl;
        this.username = username;
    }
    public ProfileDTO(){}

    public int getUserId() {
        return userId;
    }

    public String getBio() {
        return bio;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ProfileDTO{" +
                "userId=" + userId +
                ", bio='" + bio + '\'' +
                ", profilePicUrl='" + profilePicUrl + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }
}
