<template>
  <div >
             <span id="bubble">          
                <button id="show-form-button" v-on:click="showForm = true" v-on:dblclick="showForm = false" title="Add Comments">
                  <i class="far fa-comment" data-fa-transform="shrink-6" data-fa-mask="fas fa-square"> </i>
                </button>
             </span> 
           
           
           
                  <form v-on:submit.prevent="addComment" v-if="showForm === true" class="container">
                        <span>
                          <input id="comment" type="text" v-model="comment.comment" placeholder="type your comment here..."/>
                        </span>
                     
                        <span id="post-button">
                          <input type="submit" value="Post" />
                        </span>
                  </form>
          
  
  </div>
</template>

<script>
import ServerService from "../services/ServerService.js";

export default {
  name: "add-comments",
  props: ["picId"],
  data() {
    return {
      showForm: false,
      comment: {
        username: this.$store.state.user.username,
        photoId: this.picId,
      },
    };
  },

  methods: {
    addComment() {
      ServerService.addNewComment(this.comment).then(() => {
        console.log("Added Comment");
        this.resetForm();
        //TODO: redirect to photo details view
        location.reload();
      });
    },
    resetForm() {
      this.showForm = false;
      this.comment = {};
    },
  },
};
</script>

<style>

/* .comment-form {
  margin: 200px;
} */

/* .form-element {
  height: 10px;
} */

span {
  padding: 0px 3px;
} 
button {
  padding: 0;
  border: none;
  background: none;
}
/* .form-element {
  padding: 100px;
} */

.container{
  display: flex;
   flex-direction: row;
} 

input[type=text]{
  font-size: medium;
}

input[type=submit] {
  font-size: medium;
  
 
}
#bubble {
  font-size: 28px;
}


</style>
