<template>
  <div id="app">
    <Navigation/>
    <div>
      
      <router-view />  <!-- the vue component will display here -->
    </div>
  </div>
</template>

<script>
import Navigation from "./components/Navigation.vue"
export default {
  name: "app",
  components: {
    Navigation
  }
};
</script>
