<template>
  <div class="container mx-auto my-6 flex  h-screen">
      <div class="w-2/12">
        <Menu />
      </div>
      <div class="w-10/12">
          <router-view></router-view>
      </div>
  </div>
</template>

<script>
import Menu from '../components/Menu/Menu.vue'
export default {
  components: { Menu },
    name:'Home'
}
</script>

<style>

</style>