<template>
  <div class="main-comment-containter">

<div class="clock">
<button id="comment-history-btn" title="View Comment History" v-on:click="flipStatus()" >
           <i class="fa fa-history" aria-hidden="true"></i>

   </button>
   </div>


    <div v-for="comment in allComments.slice(0, 2)" :key="comment.id">
     
<div class="comments">
    <p class="name">{{comment.username}}</p>
  <p class="text">{{comment.comment}}</p>
  </div>

    </div>

    

    <div id="show-all-comments" v-if="showComments === true">
      <div v-for="comment in allComments.slice(2)" :key="comment.id">

<div class="comments">
  <p class="name">{{comment.username}}</p>
  <p class="text">{{comment.comment}}</p>
  </div>
        

      </div>
    </div>
  </div>
</template>

<script>
import ServerService from "../services/ServerService.js";
export default {
  name: "view-comments",
  props: ["picId", 
  ], 
  data() {
    return {
      showComments: false,
      allComments: [],
      comment: {
        photoId: this.picId,
      }
    };
  },
  created() {
    ServerService.listCommentsByPhoto(this.picId).then((response) => {
      console.log(response.data);
      this.allComments = response.data;
    });
  },
  methods: {

    flipStatus() {
      if (this.showComments === false) {
        this.showComments = true;
      } else if(this.showComments === true) {
        this.showComments = false;
      }
    }
  }
};
</script>

<style>
#comment-history-btn {
   border: none;
}
span{
  font-size: smaller;
}
.comments {
  font-size: 16px;
  display: flex;
  flex-direction: row;
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif
}
.name {
  color: #861657;
  margin-right: 4px;
  font-weight: bold;
}





</style>