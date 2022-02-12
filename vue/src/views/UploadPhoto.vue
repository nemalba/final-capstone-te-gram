<template>
  <div>
   
              <!-- <button @click="openUploadModal">Upload files</button>  -->
     
   
</div>
</template>

<script>


export default {
  name: 'upload-photo',
  components: {
  
  
  },
  data() {
    return {
      url: '',
    }
  },
  created(){
    this.openUploadModal(); 
  },

  mounted() {

  },
  methods: {
    openUploadModal() {
      window.cloudinary.openUploadWidget(
        { cloud_name: 'te-gram2022',
          upload_preset: 'o1kxq5jo'
        },
        (error, result) => {
          if (!error && result && result.event === "success") {
            console.log('Done uploading..: ', result);
            this.$store.state.upload = result.info;
            console.table(this.$store.state.upload);  
            const url = this.$store.state.upload.secure_url;
            console.log(url);
            this.$router.push({ name: 'captionPhoto', query: 
            { redirect: '/captionPhoto' }})
            }
        }).open();

        
     }
}
}
</script>

<style>

  

</style>