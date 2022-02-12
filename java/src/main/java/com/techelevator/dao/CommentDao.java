package com.techelevator.dao;

import com.techelevator.model.CommentDTO;

import java.util.List;

public interface CommentDao {

    void createNewComment(CommentDTO newComment);

    List<CommentDTO> getAllCommentsByPhotoId(int photoId);
}
