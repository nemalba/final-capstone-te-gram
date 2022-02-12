package com.techelevator.dao;

import com.techelevator.model.ProfileDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcProfileDAO  implements ProfileDAO{
    private JdbcTemplate template;

    public JdbcProfileDAO(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public void addProfileInfo(ProfileDTO profileDTO) {
        String sql = "INSERT INTO profile(user_id, bio, profile_pic_url) " +
                "VALUES (?,?,?)";
        int userId = profileDTO.getUserId();
        String bio = profileDTO.getBio();
        String profilePicUrl = profileDTO.getProfilePicUrl();

        try{
            int id = template.queryForObject(sql,Integer.class,userId,bio, profilePicUrl);
        }catch (Exception e) {}
    }

    @Override
    public ProfileDTO viewUserProfile(int userId) {
        String sql = "SELECT username, profile_pic_url, bio FROM profile p " +
                " JOIN users u on p.user_id = u.user_id " +
                " WHERE p.user_id = ? ";

        SqlRowSet result = template.queryForRowSet(sql, userId);
        ProfileDTO profileDTO = new ProfileDTO();

        System.out.println(result);

        while(result.next()) {
            String username = result.getString("username");
        String profilePicUrl = result.getString("profile_pic_url");
        String bio = result.getString("bio");

        profileDTO.setUsername(username);
        profileDTO.setProfilePicUrl(profilePicUrl);
        profileDTO.setBio(bio);

        }
        return profileDTO;
    }
}
