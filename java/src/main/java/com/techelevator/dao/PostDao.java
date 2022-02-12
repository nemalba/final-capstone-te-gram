package com.techelevator.dao;

import com.techelevator.model.LikeDTO;
import com.techelevator.model.PostDTO;

import java.util.List;

public interface PostDao {

    void createNewPost(PostDTO newPost);

    List<PostDTO> getAllPostsByUser(int userId);

    List<PostDTO> photoFeed();

    void newLike(LikeDTO newLike);

    int numberOfLikes(int photoId);

    void deleteLike(LikeDTO deleteLike);

    boolean isItLiked(LikeDTO likeObject);





}
