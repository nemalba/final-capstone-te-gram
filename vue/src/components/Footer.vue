<template>
  <div>
     
            <div class="footer">
                <p>
                    <router-link :to="{ name: 'about' }">{{message}}</router-link> &nbsp;&nbsp; |
                    <router-link :to="{ name: 'contact' }">{{message1}}</router-link>
                </p>
                <p>&copy; TE-gram.com | Brought to you by The Multiverse Team</p>
            </div>
  </div>
</template>

<script>
export default {
        name: 'footer-section',

        data(){
            return {
                message:'About',
                message1:'Contact Us',
                
            }
        }
    
}
</script>

<style>
  .footer {
   position: fixed;
   left: 0;
   bottom: 0;
   width: 100%;
   background-color: rgb(238, 232, 232);
   color: rgb(49, 48, 48);
   text-align: center;
}


</style>