<template>
  <div class="container mx-auto">
      <div class="flex flex-col text-center space-y-6 mt-24">
            <h1 class="font-bold font-mono text-6xl underline">
                PANEL
            </h1>
            <input type="text" name="username" id="username" placeholder="admin@kayafirat.com" class="border px-2 py-1 rounded focus:outline-none focus:ring-2 w-96 mx-auto" v-model="username">
            <input type="password" name="password" id="username" placeholder="Password" class="border px-2 py-1 rounded focus:outline-none focus:ring-2 w-96 mx-auto" v-model="password">
            <button class="bg-blue-500 w-36 text-white rounded px-2 py-1 mx-auto hover:bg-blue-400 transition duration-300 focus:ring-4" @click="submit">Login</button>
      </div>
  </div>
</template>

<script>
import service from '../service/service.js'
import { mapActions, mapGetters } from 'vuex'

export default {
  name:'Login',
  data(){
    return {
      username:'',
      password:''
    }
  },
  methods:{
    ...mapActions('user',['loggedUser']),
    ...mapGetters('user',['getLoggedUser']),
    submit() {
      const body = {
          username : this.username,
          password : this.password
      }
      service.login(body)
        .then(() => {
            this.loggedUser();
        })
    }
  },
  computed:{
      currentUser(){
          return this.getLoggedUser();
      }
  },
  mounted(){
      if(Object.keys(this.getLoggedUser()).length === 0) {
          this.loggedUser();
      }
  }

}
</script>

<style>

</style>