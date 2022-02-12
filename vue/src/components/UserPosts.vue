<template>
<div>
  <div >
     <top-menu />
  </div>
       <div id="main-container">
         <!-- pic and bio -->
          <div class="container">
            <span class="tiny-pic-dad bio-padding">
            <img :src="profile.profilePicUrl"  class= "tiny-pic"/>
            </span >
            <span class="bio-padding">{{profile.bio}}</span>
           </div>
            <!-- Add Bio - Save Profile -->
                 <div class="container-bio" >
                  <form v-on:submit.prevent="submitProfile()" v-if="this.$store.state.user.username === this.$route.params.username " class="container-bio">
                        <input type="text" v-model="newProfile.bio" placeholder="Add your bio here!" />
                        <div class="navigation-link-fa">
                            <button @click="openUploadModal" tag="i" class="fa fa-plus-square"  title="Upload Bio"></button>
                        </div>
                        <div class="navigation-link"> <input type="submit" value="Save Profile" /></div>
                      </form>
                  </div>
              <!-- Add Bio - Save Profile -->
          </div>
        <!-- pic and bio -->
        <div class="flex-container">
                    <div v-for="pic in userPictures" :key="pic.Id">
                    <!-- <div v-bind:pic-username="pic.username">{{ pic.username }}</div> -->
                    <h1>{{pic.username}}</h1>
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
 <footer-section></footer-section>
</div>
</template>
<script>
import serverService from "../services/ServerService.js";
import AddComments from "../components/AddComments.vue";
import ViewComments from "../components/ViewComments.vue";
import LikeButton from "../components/LikeButton.vue";
import AddToFavorites from "../components/AddToFavorites.vue";
import TopMenu from "../components/TopMenu.vue";
import FooterSection from '../components/Footer.vue'
export default {
    name: 'userPosts',
    props: ["selectedUser"],
    components: {
    AddComments,
    ViewComments,
    LikeButton,
    AddToFavorites,
    TopMenu,
    FooterSection,
  },
      data() {
    return {
      profileUrl: '',
      userPictures: [],
      profile: {
        username : this.$route.params.username,
      },
      newProfile: {
        username: this.$store.state.user.username,
      }
    }
  },
  methods: {
      openUploadModal() {
      window.cloudinary
        .openUploadWidget(
          { cloud_name: "te-gram2022", upload_preset: "o1kxq5jo" },
          (error, result) => {
            if (!error && result && result.event === "success") {
              this.$store.state.upload = result.info;
              console.table(this.$store.state.upload);
                const profileUrl = this.$store.state.upload.secure_url;
               console.log(profileUrl)
            }
          }
        )
        .open();
    },
    submitProfile() {
      this.newProfile.profilePicUrl = this.$store.state.upload.secure_url;
      serverService.addNewProfile(this.newProfile)
    }
  },
    created() {
serverService.listByUser(this.$route.params.username).then((response) => {
        console.log(response.data);
        this.userPictures = response.data;
      });
      serverService.getProfile(this.profile.username).then((response) => {
        this.profile = response.data;
      })
    }
}
</script>
<style>
.box{
    padding: 150px;
}
#main-container{
  display: flex;
  flex-direction: column;
}
.container{
  margin-top:100px;
  display: flex;
  flex-direction: row;
  align-items: center;
   row-gap: 10px;
   align-content: space-between;
   /* background-color: rgb(245, 245, 240); */
}
.container-bio{
  margin-top:5px;
  margin-left:180px;
  display: flex;
  flex-direction: row;
  align-items: center;
}
.flex-container {
  padding: 150px;
  display: flex;
  align-items: center;
}
.tiny-pic{
  width: 100px;
  height: 100px;
  border-radius: 50%;
}
.tiny-pic-dad{
  padding: 100px;
}

.bio-padding{
   margin:0px;
   padding:5px;
}
.space-in{
  margin-left:20px;
}
.space-right{
  padding-left: 200px;
}
.navigation-link{
  padding-left: 20px;
}
.navigation-link-fa{
  padding-left: 15px;
  font-size: xx-large;
}
#com-bubble{
  padding-right: 65px;
}
</style>