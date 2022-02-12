<template>
  <div>
     <div>
       <top-menu></top-menu>
     </div>
     
      <!-- place photo thumbnail here -->
      <div id="container">
          <div class="center">
              <img v-bind:src="$store.state.upload.url" alt="photo thumbnail" width="700px" length= "700px"/> 
          </div>
          <div class="center">
              <form v-on:submit.prevent='newPost'>
                <input type="text" v-model="newPic.caption" placeholder="caption your photo" />
                <input type="submit"/>
              </form>
          </div>
           
      </div>
     <footer-section></footer-section>
  </div>
</template>

<script>
import ServerService from '../services/ServerService.js'
import TopMenu from '../components/TopMenu.vue'
import FooterSection from '../components/Footer.vue'

export default {

  components: {
    TopMenu,
     FooterSection,
  },
  data () {
    return {
      newPic: {
        username: this.$store.state.user.username,
        url: this.$store.state.upload.url
      }
    }
  },

  created() {
    console.log(this.$store.state.upload.url)
  },

  methods: {
    newPost() {
      ServerService.addNewPic(this.newPic).then(
        () => {
          console.log('Added Photo');
          console.log(this.$store.state.user.username);
          this.$router.push({ name: 'home', query: 
            { redirect: '/' }});
        }
      )
    },
    test(){
      console.log('Kinda thingy')
    }
  }
}
</script>

<style>
 
  #container{
    display: flex;
    flex-direction: column ;
    align-items: center;
    justify-content: space-between;
    align-content: space-around;
  
  }
  .center{
    display:flex;
    align-items:center;
  }
 
</style>