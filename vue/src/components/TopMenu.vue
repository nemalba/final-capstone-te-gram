<template>
  <div>
    <!-- class="navigation" -->
    <nav>
      <div class="navigation">
        <div class="logo">
          <a class="no-underline" href="#">{{ message }}</a>
        </div>

        <br />
        

        <!-- home button -->
        <div class="navigation-icons">
          <a title="home" href="" target="_blank" class="navigation-link">
            <router-link
            
              v-bind:to="{ name: 'home' }"
              tag="i"
              class="fa fa-home"
            ></router-link>
          </a>

          
          <!-- add photo button -->
          <div class="navigation-link" title="new post">
            <button
              @click="openUploadModal"
              tag="i"
              class="fa fa-plus-square"
            ></button>
          </div>

         

          <!-- view my favorites button -->
          <router-link 
          v-bind:to="{ name: 'favoriteFeed' }">
            <a class="navigation-link notifica" title="my favorites">
              <i class="far fa-star">
                <div class="notification-bubble-wrapper">
                  <div class="notification-bubble">
                    <span class="notifications-count">99</span>
                  </div>
                </div>
              </i>
            </a>
          </router-link>
         

          <!-- my profile button -->
                    <router-link :to="{name: 'userPage', params: {username: this.$store.state.user.username }}">
            <a class="navigation-link notifica" title="my profile">
              <i class="far fa-user-circle">
                <div class="notification-bubble-wrapper">
                  <div class="notification-bubble">
                    <span class="notifications-count">99</span>
                  </div>
                </div>
              </i>
            </a>
          </router-link>


          <!--Logout widget  -->
          <a href="" id="signout" class="navigation-link" title="signout">
            <router-link
              v-bind:to="{ name: 'logout' }"
              tag="i"
              class="fas fa-sign-out-alt"
            ></router-link>
          </a>


        </div>
      </div>
    </nav>

    <div>
      <div></div>
    </div>
  </div>
</template>

<script>

export default {
  name: "top-menu",

  data() {
    return {
      message: "TE-gram",
    };
  },

  methods: {
    openUploadModal() {
      window.cloudinary
        .openUploadWidget(
          { cloud_name: "te-gram2022", upload_preset: "o1kxq5jo" },
          (error, result) => {
            if (!error && result && result.event === "success") {
              console.log("Done uploading..: ", result);
              this.$store.state.upload = result.info;
              console.table(this.$store.state.upload);
              const url = this.$store.state.upload.secure_url;
              console.log(url);
              this.$router.push({
                name: "captionPhoto",
                query: { redirect: "/captionPhoto" },
              });
            }
          }
        )
        .open();
    },
  },
};
</script>

<style>
.navigation {
  background-color: #ffffff;
  height: 80px;
  position: fixed;
  width: 100%;
  top: 0;
  left: 0;
  border-bottom: 1px solid rgba(0, 0, 0, 0.0975);
  display: flex;
  align-items: center;
  justify-content: space-around;
  padding: 10px 50px;
  box-sizing: border-box;
  z-index: 2;
  /* animation magic */
  transition: all 0.4s ease-in-out;
  -webkit-transition: all 0.4s ease-in-out;
  -moz-transition: all 0.4s ease-in-out;
}

.navigation-link button {
  margin-left: 30px;
  color: black;
  text-decoration: none;
  font-size: 22px;
}
</style>