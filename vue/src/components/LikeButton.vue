<template>
  <div>
    <span>
      <button
        id="like-button"
        v-on:click.prevent="flipStatus($event)"
        title="Like Photo"
      >
        <i
          class="far fa-heart"
          data-fa-transform="shrink-10 up-.5"
          data-fa-mask="fas fa-comment"
        ></i></button
    >
    </span>
    <sub class="count">{{ this.likeCount }}</sub>
    
    
  </div>
</template>

<script>
import ServerService from "../services/ServerService.js";
export default {
  name: "like-button",
  props: ["picId"],
  data() {
    return {
      likeCount: 0, 
      like: {
        username: this.$store.state.user.username,
        photoId: this.picId,
        isLiked: false,
      },
    };
  },
  created() {
    ServerService.likeCount(this.like.photoId).then((response) => {
      this.likeCount = response.data;
      }),

    ServerService.getLikedStatus(this.like).then((response) =>{
      this.like.isLiked = response.data;
      })
   
  },
  methods: {
  
    flipStatus(event) {
      const icon = event.target;
        if(!this.like.isLiked) {
          ServerService.addLike(this.like).then(() => {
        this.likeCount +=1;
        this.like.isLiked = true
        icon.classList.add("change-color");
      });
        
        } else if(this.like.isLiked) {
            ServerService.subtractLike(this.like).then(() => {});
            this.likeCount -=1;
            this.like.isLiked = false;
            icon.classList.remove("change-color");
        }
      
    }
    
  }
};
</script>

<style>
.change-color {
  color: #ffa69e;
}
.count {
  font-size: small;
}
#like-button{
  font-size: 28px;
}
</style>