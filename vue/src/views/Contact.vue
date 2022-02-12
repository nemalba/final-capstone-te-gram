<template>
  <div>
     <top-menu></top-menu>
        <h3>The Multiverse Team</h3>
   
    <div >
     <ul>
       <li><a href="https://www.linkedin.com/in/nema-aragones-798b429b/">Nema Aragones</a></li>
      <li><a href="https://www.linkedin.com/in/isaiahtoliver/">Isaiah Toliver</a></li>
      <li><a href="https://www.linkedin.com/in/dughlas-snider/">Aron Hashbarger</a></li>
      <li><a href="https://www.linkedin.com/in/stephanie-konczal/">Stephanie Konczal</a></li>
      </ul>
    </div>
    
     <footer-section></footer-section>
  </div>
</template>

<script>
import TopMenu from "../components/TopMenu.vue";
  import FooterSection from "../components/Footer.vue";
export default {
  name:"contact",
  components: {
     TopMenu, 
    FooterSection
  },
}
</script>

<style>
 body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=submit] {
  background-color: #04AA6D;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=submit]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}

h3{
  text-align: center;
  margin-top: 150px;
  padding-left: 5px;
  
 
}
h4{
  text-align: right;
  padding-right: 200px;
}
ul{
  font-family: "Lucida Console";
  font-size: 20px;
  text-align: center;
  list-style-type: none;
  line-height: 100px;
  padding-top: 10px;
  padding-right: 30px;
}

</style>