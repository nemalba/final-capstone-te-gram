<template>
  <div>
    <section>
      <div class="main-flex-container">
        <div class="flex-container">
          <div v-for="pic in allPictures" :key="pic.Id">
            

              
           
              <router-link :to="{name: 'userPage', params: {username: pic.username}}" class="routerlink">{{pic.username}}</router-link>
            

            <p><img :src="pic.url" width="300px" length="300px" alt="" /></p>
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
                      <span class="space-in">
                        <view-comments v-bind:pic-id="pic.photoId" />
                      </span>
                      <span class="space-in">
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
import serverService from "../services/ServerService.js";
import ViewComments from "../components/ViewComments.vue";
import LikeButton from "../components/LikeButton.vue";
import AddToFavorites from "../components/AddToFavorites.vue";

export default {
  name: "postcard-2",
  components: {
    AddComments,
    ViewComments,
    LikeButton,
    AddToFavorites
  },

  computed: {
    getSelectedUser () {
      return this.$store.state.selectedUser
    }
  },
  
  methods: {
    redirect() {
this.$router.push({ name: 'userPage', query: 
            { redirect: '/profile/:username' }})
    }
  },

  changeSelectedUser(selectedUser) {
    this.$store.commit("CHANGE_SELECTED_USER", selectedUser)
  },

  data() {
    return {
      allPictures: [],
      selectedUser: "",
    };
  },
  created() {
    serverService.listAll().then((response) => {
      console.log(response.data);
      this.allPictures = response.data;
    }),
      serverService.listFavorites().then((response) => {
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

.routerlink {
  color: rgba(37, 37, 37, 0.856);
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif
}

.space-in{
  margin-left:20px;
}
.space-right{
  padding-left: 10px;
}
.caption{
  font-size: 20px;
   font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif
}

</style>