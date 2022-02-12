package com.techelevator.dao;

import com.techelevator.model.ProfileDTO;


public interface ProfileDAO {

    void addProfileInfo(ProfileDTO profileDTO);

    ProfileDTO viewUserProfile(int userId);
    
}
