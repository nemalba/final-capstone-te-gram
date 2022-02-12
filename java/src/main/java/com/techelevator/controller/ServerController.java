package com.techelevator.controller;

import com.techelevator.dao.*;
import com.techelevator.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@PreAuthorize("isAuthenticated()")
public class ServerController {

    @Autowired
    PostDao postDao;
    @Autowired
    UserDao userDao;
    @Autowired
    CommentDao commentDao;
    @Autowired
    FavoriteDAO favoriteDao;
    @Autowired
    ProfileDAO profileDAO;



    @RequestMapping(path="/addPost", method = RequestMethod.POST)
    public void createPost(@RequestBody PostDTO newPost) {
        int id = userDao.findIdByUsername(newPost.getUsername());
        String caption = newPost.getCaption();
        String url = newPost.getUrl();

        PostDTO postablePost = new PostDTO();
        postablePost.setCaption(caption);
        postablePost.setUrl(url);
        postablePost.setUserId(id);

        postDao.createNewPost(postablePost);
    }

    @RequestMapping(path="/viewPosts/{username}", method = RequestMethod.GET)
    public List<PostDTO> viewUserPosts(@PathVariable("username") String username) {
        int id = userDao.findIdByUsername(username);
        return postDao.getAllPostsByUser(id);
    }

    @RequestMapping(path="/homeFeed", method = RequestMethod.GET)
    public List<PostDTO> HomeFeed() {
        return postDao.photoFeed();
    }

    @RequestMapping(path="/viewComments/{photoId}", method = RequestMethod.GET)
    public List<CommentDTO> viewPostComments(@PathVariable("photoId") int photoId){
        return commentDao.getAllCommentsByPhotoId(photoId);
    }

    @RequestMapping(path="/addComment", method = RequestMethod.POST)
    public void createComment(@RequestBody CommentDTO createdComment) {
        int userId = userDao.findIdByUsername(createdComment.getUsername());
        String comment = createdComment.getComment();
        int photoId = createdComment.getPhotoId();

        CommentDTO sendComment = new CommentDTO();
        sendComment.setUserId(userId);
        sendComment.setComment(comment);
        sendComment.setPhotoId(photoId);

        commentDao.createNewComment(sendComment);
    }

    @RequestMapping(path="/addLike", method = RequestMethod.POST)
    public void addLike(@RequestBody LikeDTO addedLike) {
        int userId = userDao.findIdByUsername(addedLike.getUsername());

        LikeDTO newLike = new LikeDTO();
        newLike.setPhotoId(addedLike.getPhotoId());
        newLike.setUserId(userId);

        postDao.newLike(newLike);

    }

    @RequestMapping(path="/countLike/{photoId}", method = RequestMethod.GET)
    public int numberOfLikes (@PathVariable("photoId") String photoId){
        return postDao.numberOfLikes(Integer.parseInt(photoId));
    }

    @RequestMapping(path="/removeLike", method = RequestMethod.DELETE)
    public void subtractLike(@RequestBody LikeDTO subtractedLike) {
        int userId = userDao.findIdByUsername(subtractedLike.getUsername());

        LikeDTO removedLike = new LikeDTO();
        removedLike.setUserId(userId);
        removedLike.setPhotoId(subtractedLike.getPhotoId());

        postDao.deleteLike(removedLike);
    }

    @RequestMapping(path="/isItLiked", method = RequestMethod.POST)
    public boolean liked(@RequestBody LikeDTO likeDto) {
        int userId = userDao.findIdByUsername(likeDto.getUsername());

        LikeDTO newLikeDto = new LikeDTO();
        newLikeDto.setUserId(userId);
        newLikeDto.setPhotoId(likeDto.getPhotoId());

        return postDao.isItLiked(newLikeDto);
    }

    @RequestMapping(path="/myFavorites/{username}", method = RequestMethod.GET)
    public List<PostDTO> favoritesOfUser(@PathVariable("username") String username) {
        int id = userDao.findIdByUsername(username);
        return favoriteDao.favoriteFeed(id);
    }

    @RequestMapping(path= "/newFav", method = RequestMethod.POST)
    public void addFav( @RequestBody FavoriteDTO fav) {
        int userId = userDao.findIdByUsername(fav.getUsername());

        FavoriteDTO addingFav = new FavoriteDTO();
        addingFav.setUserId(userId);
        addingFav.setPhotoId(fav.getPhotoId());

        favoriteDao.addToFavs(addingFav);
    }

    @RequestMapping(path="/addProfInfo", method= RequestMethod.POST)
    public void addProfileInfo(@RequestBody ProfileDTO newProfInfo) {

        System.out.println("What I'm getting from client:");
        System.out.println(newProfInfo);

        int userId = userDao.findIdByUsername(newProfInfo.getUsername());

        ProfileDTO newInfo = new ProfileDTO();
        newInfo.setUserId(userId);
        newInfo.setBio(newProfInfo.getBio());
        newInfo.setProfilePicUrl(newProfInfo.getProfilePicUrl());

        profileDAO.addProfileInfo(newInfo);
    }

    @RequestMapping(path="/profile/{username}", method= RequestMethod.GET)
    public ProfileDTO viewUserProfile(@PathVariable String username) {
        int userId = userDao.findIdByUsername(username);
        
        return profileDAO.viewUserProfile(userId);
    }

    @RequestMapping(path="/removeFavorite", method = RequestMethod.DELETE)
    public void deleteFavorite(@RequestBody FavoriteDTO lostFav) {
        int userId = userDao.findIdByUsername(lostFav.getUsername());

        FavoriteDTO removedFavorite = new FavoriteDTO();
        removedFavorite.setUserId(userId);
        removedFavorite.setPhotoId(lostFav.getPhotoId());

        favoriteDao.removeFromFavs(removedFavorite);
    }

    @RequestMapping(path="/isItFaved", method = RequestMethod.GET)
    public boolean faved(@RequestBody FavoriteDTO favoriteDTO) {
        int userId = userDao.findIdByUsername(favoriteDTO.getUsername());

        FavoriteDTO favoriteObject = new FavoriteDTO();
        favoriteObject.setUserId(userId);
        favoriteObject.setPhotoId(favoriteDTO.getPhotoId());

        return favoriteDao.isItFaved(favoriteObject);
    }

}
