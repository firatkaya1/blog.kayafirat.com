<template>
  <AlertList />
  <router-view />
</template>

<script>
import AlertList from './components/Alert/AlertList.vue'

export default {
  components: { AlertList },
  name: 'App',
 
}
</script>

<style>

</style>
