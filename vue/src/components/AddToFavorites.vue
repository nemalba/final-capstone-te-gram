<template>
  <div>
    <span >
      <button
      title="favorite"      id="star" v-on:click.prevent="flipStatus()">
        <i
          class="far fa-star"
          data-fa-transform="shrink-3.5 down-1.6 right-1.25"
          data-fa-mask="fas fa-circle"
        ></i>
      </button>
    </span>
  </div>
</template>

<script>
import ServerService from "../services/ServerService.js";
export default {
  name: "favorites",
  props: ["picId"],
  data() {
    return {
      favorite: {
        username: this.$store.state.user.username,
        photoId: this.picId,
        isFavorited: false
      },
      newFavorite: {
        username: this.$store.state.user.username,
        photoId: this.picId,
        isFavorited: false
      }
    };
  },
  created() {
    ServerService.getFavStatus(this.favorite).then((response) =>{
      this.newFavorite.isFavorited = response.data;
    })
  },
  methods: {

    flipStatus(){
      if(!this.isFavorited) {
        ServerService.addToFavorites(this.favorite).then( () =>
        this.isFavorited = true
        )
      } else if (this.isFavorited) {
        ServerService.removeFavorite(this.favorite).then( () =>
        this.isFavorited = false
        )
      }
    }
  }
};
</script>

<style>
#star{
  font-size: 28px;
}
</style>