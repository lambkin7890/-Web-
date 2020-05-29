<template>
  <div id="app">    
    <!-- 路由占位符 -->
    <router-view v-if="isRouterAlive" /> 
  </div>
</template>

<script>
export default {
  name: 'app',
  data () {
    return {
      isRouterAlive: true
    }
  },
  mounted() {
      window.addEventListener('unload',this.saveState)
        
  },
  methods: {
    saveState(){
      localStorage.setItem("state",JSON.stringify(this.$store.state))
    }
  },
};
</script>

<style>
</style>
