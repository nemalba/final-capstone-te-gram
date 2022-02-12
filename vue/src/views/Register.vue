<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">TE-gram</h1>
      <p>Sign up to see photos from your friends.</p>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
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
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <br />


      <!-- class="btn btn-lg btn-primary btn-block" -->
      <button type="submit">Sign up</button>
      <br />
      <br />
      <router-link :to="{ name: 'login' }">Have an account?</router-link>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";
export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    }
  },
};
</script>

<style scoped>
@import url("//fonts.cdnfonts.com/css/billabong");

h1 {
  font-family: "billabong", sans-serif;
  font-weight: bold;
}
#register {
  /* background-image: url("../assets/background.jpg");
  background-size: cover; */
  background-color: #fffafa;
  position: absolute;
  top: 95px;
  right: 0px;
  bottom: 0px;
  left: 0px;
}

h1 {
  font-family: "billabong", sans-serif;
  font-weight: bold;
}
.form-register {
  text-align: center;
}
#register button {
  width: 120px;
  height: 30px;
  border-radius: 4px;
  border-style: solid;
  border-width: thin;
  font-weight: bold;
}
.form-register {
  text-align: center;
  width: 300px;
  margin: 60px auto 30px;
  border-radius: 15px;
  background-color: rgba(255, 255, 255, 0.6);
  padding: 10px;
}
</style>
