package com.techelevator.dao;

import com.techelevator.model.FavoriteDTO;
import com.techelevator.model.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcFavoriteDAO  implements FavoriteDAO{
    private JdbcTemplate template;
    @Autowired
    UserDao userDao;

    public JdbcFavoriteDAO(JdbcTemplate template) {
        this.template = template;
    }


    public List<PostDTO> favoriteFeed(int id) {
        String sql = "Select f.photo_id, p.photo_url, u.username, p.caption from favorites f " +
                "join photos p on f.photo_id = p.photo_id " +
                "join users u on p.user_id = u.user_id " +
                "WHERE f.user_id = ?;";
        List<PostDTO> userPosts = new ArrayList<>();
        SqlRowSet result = template.queryForRowSet(sql, id);

        while (result.next()) {
            int photoId = result.getInt("photo_id");
            String photoUrl = result.getString("photo_url");
            String photoCaption = result.getString("caption");
            String username = result.getString("username");

            PostDTO postDTO = new PostDTO();
            postDTO.setPhotoId(photoId);
            postDTO.setUrl(photoUrl);
            postDTO.setCaption(photoCaption);
            postDTO.setUsername(username);
            userPosts.add(postDTO);
        }
        return userPosts;
    }

    @Override
    public void addToFavs(FavoriteDTO fav) {
        try {
            String sql = "Insert INTO favorites (user_id, photo_id, is_favorited) " +
                    "VALUES(?,?,?)";
            int id = template.queryForObject(sql, Integer.class, fav.getUserId(), fav.getPhotoId(), true);
        }catch(Exception e) {
            System.out.println("Some text that i put in here");
        }
    }

    @Override
    public void removeFromFavs(FavoriteDTO deleteFav) {
        String sql = "DELETE FROM favorites " +
                "WHERE user_id = ? AND photo_id = ?";

        int userId = deleteFav.getUserId();
        int photoId = deleteFav.getPhotoId();

        template.update(sql, userId, photoId);
    }

    @Override
    public boolean isItFaved(FavoriteDTO favoriteDTO) {
        String sql = "Select is_favorited from favorites " +
                "WHERE user_id = ? AND photo_id = ? ";
        int userId = favoriteDTO.getUserId();
        int photoId = favoriteDTO.getPhotoId();

        try {
            if (template.queryForObject(sql, Boolean.class, userId, photoId) == true) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }
}
