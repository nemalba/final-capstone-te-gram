package com.techelevator.dao;

import com.techelevator.model.CommentDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class JdbcCommentDao implements CommentDao{
    private JdbcTemplate template;
    public JdbcCommentDao(JdbcTemplate template) {this.template = template;}


    @Override
    public void createNewComment(CommentDTO newComment) {
        int userId = newComment.getUserId();
        String comment = newComment.getComment();
        int photoId = newComment.getPhotoId();

        try {
            String sql = "INSERT INTO comments(comment, user_id, photo_id) " +
                    "VALUES(?,?,?)";
             int id = template.queryForObject(sql, Integer.class, comment, userId,photoId);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }



    }

    @Override
    public List<CommentDTO> getAllCommentsByPhotoId(int photoId) {
        String sql = "Select username, comment_id, comment " +
                "FROM comments c " +
                "JOIN users u ON c.user_id = u.user_id " +
                "WHERE photo_id = ?" +
                "ORDER BY comment_id DESC";
        List<CommentDTO>commentsOnPhoto = new ArrayList<>();
        SqlRowSet result = template.queryForRowSet(sql,photoId);

        while(result.next()) {
            String username = result.getString("username");
            int commentId = result.getInt("comment_id");
            String comment = result.getString("comment");

            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setUsername(username);
            commentDTO.setCommentId(commentId);
            commentDTO.setComment(comment);
            commentsOnPhoto.add(commentDTO);
        }
        return commentsOnPhoto;

    }


    public int getPhotoIdByUrl(String url) {
        String sql = "Select photo_id " +
                "FROM photos " +
                "WHERE photo_url = ?";

        int id = template.queryForObject(sql,Integer.class,url);
        return id;
    }


}
