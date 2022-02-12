<template>
  <div>
    <top-menu />
    {{this.myFavoritePictures}}
    {{this.favorite.username}}
    {{this.favorite.photoId}}
    <section>
      <div class="main-flex-container">
        <div class="flex-container">
          <div v-for="pic in myFavoritePictures" :key="pic.Id">
            <p class="poster-name">{{ pic.username }}</p>
            <p><img :src="pic.url" width="500px" length="500px" alt="" /></p>
            <p class="caption">{{ pic.caption }}</p>
            <div v-bind:src="pic"></div>

            <!-- call buttons here -->
             <div class="buttons">
                      <span class="space-in">
                        <like-button v-bind:pic-id="pic.photoId" />
                      </span>
                      <span class="space-in" >
                        <add-comments v-bind:pic-id="pic.photoId" />
                      </span>
                      <span class="space-in" id="com-bubble">
                        <view-comments v-bind:pic-id="pic.photoId" />
                      </span>
                      <span class="space-right">
                        <add-to-favorites v-bind:pic-id="pic.photoId" />
                      </span>
                    </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import AddComments from "../components/AddComments.vue";
import ServerService from "../services/ServerService.js";
import ViewComments from "../components/ViewComments.vue";
import LikeButton from "../components/LikeButton.vue";
import AddToFavorites from "../components/AddToFavorites.vue";
import TopMenu from "../components/TopMenu.vue";

export default {
  name: "favoriteFeed",
  components: {
    AddComments,
    ViewComments,
    LikeButton,
    AddToFavorites,
    TopMenu
  },

  data() {
    return {
      myFavoritePictures: [],
      favorite: {
        username: this.$store.state.user.username
      },
      
    };
  },
  created() {
    ServerService.listFavorites(this.$store.state.user.username).then((response) => {
      this.myFavoritePictures = response.data;
    });
  }
};
</script>

<style>
.main-flex-container {
  display: flex;
  justify-content: center;
  background-color: rgb(245, 245, 240);
  padding: 10px;
  margin: 20px;
}
.flex-container > div {
  background-color: #f1f1f1;
  margin: 10px;
  padding: 20px;
  font-size: 30px;
}

.buttons {
  display: flex;
}

.change-color {
  color: #ffa69e;
}
.count {
  font-size: small;
}
.space-in{
  margin-left:20px;
}
.space-right{
 
  padding-left: 500px;
}
#com-bubble{
  padding-right: 200px;
}
</style>