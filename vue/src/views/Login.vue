<template>

  <div id="login" class="text-center">

    <div class="float-container">

        <div class="float-child">
           <img alt="Vue logo" src="../assets/tegram.png">
        </div>
  
        <div class="float-child">
          <!-- Login form start here -->
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">TE-gram</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <!-- <label for="username" class="sr-only">Username</label> -->
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <br />
     
      <!-- <label for="password" class="sr-only">Password</label> -->
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <br />
       <br />
      <button type="submit">Log In</button>
      <br />
        <br />
      <span>Don't have an account? </span>
      <router-link :to="{ name: 'register' }">Sign up</router-link>
      
    </form>
        </div>
  
  </div>


<footer-section></footer-section>
 
  </div>
</template>

<script>
import authService from "../services/AuthService";
import FooterSection from "../components/Footer.vue";

export default {
  name: "login",
  components: {
     FooterSection,
  },
  data() {
    return {
        user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
@import url('//fonts.cdnfonts.com/css/billabong'); 

.float-container {
    border: 1px solid #fff;
    /* padding: 2px; */
     display: flex;
     justify-content: center;
}

.float-child {
   display: flex;
    margin: 5px;
    width: 25%;
    /* align-content: center; */
    /* float: left; */
    /* padding: 0px; */
    /* border: 2px solid red; */
}  
#login {
  /* background-image: url("../assets/background.jpg"); 
  background-size: cover; */
  background-color: #fcf7f7fd;
  position: absolute;
  top: 95px;
  right: 0px;
  bottom: 0px;
  left: 0px;
}

h1{
   font-family: "billabong", sans-serif;
   font-weight: bold;
}

#login button {
  width: 100px;
  height: 30px;
  border-radius: 4px;
  border-style: solid;
  border-width: thin;
  font-weight: bold;
}
.form-signin {
  text-align: center;
  width: 300px;
  margin: 60px auto 30px;
  border-radius: 15px;
  background-color: rgba(255, 255, 255, 0.6);
  padding: 10px;
}
</style>