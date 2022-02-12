<template>
  <div>
    <top-menu></top-menu>
    <div class="flex-container">
      <div>About</div>
      <div class="About">
        <p>TE-gram is a photo sharing application. Users can create posts, like and comment, and even save photos to a favorites list.</p>
      </div>

    </div>
     <footer-section></footer-section>
  </div>
</template>

<script>
import TopMenu from "../components/TopMenu.vue";
  import FooterSection from "../components/Footer.vue";

export default {
    name: 'about',
    components: {
     TopMenu, 
    FooterSection
  },

}
</script>

<style>
.About{
  
  font-family: "Lucida Console"
}
.flex-container {
  display: flex;
  flex-direction: column;
 }

.flex-container > div {
  background-color: #f1f1f1;
  margin: 10px;
  padding: 20px;
  font-size: 30px;
}
</style>