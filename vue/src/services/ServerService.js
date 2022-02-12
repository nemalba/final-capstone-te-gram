import axios from 'axios';



export default {
    getUserPics(username) {
        return axios.get(`/viewPosts/user/${username}`)
    },
    addNewPic(userPost){
        return axios.post('/addPost', userPost)
    },
    addNewComment(userComment){
        return axios.post('/addComment', userComment)
    },
    listByUser(username) {
        return axios.get(`/viewPosts/${username}`);
    },
    listAll() {
        return axios.get('/homeFeed')
    },
    listCommentsByPhoto(photoId) {
        return axios.get(`/viewComments/${photoId}`)
    },


    addLike(userLike) {
        return axios.post('/addLike', userLike)
    },
    subtractLike(userLike) {
        return axios.delete( '/removeLike', {data: userLike})
    },
    likeCount(photoId) {
        return axios.get(`/countLike/${photoId}`)
    },
    getLikedStatus(currentPost) {
        return axios.post('/isItLiked', currentPost)
    },

    addToFavorites(favorite) {
        return axios.post(`/newFav`, favorite)
    },
    listFavorites(username) {
        return axios.get(`/myFavorites/${username}`)
    },

    getProfile(username) {
        return axios.get( `/profile/${username}`)
    },
    addNewProfile(profile) {
        return axios.post( '/addProfInfo', profile)
    },
    removeFavorite(favorite) {
        return axios.delete('/removeFavorite', favorite)
    },
    getFavStatus(favorite) {
        return axios.get('/isItFaved' , favorite)
    }

}