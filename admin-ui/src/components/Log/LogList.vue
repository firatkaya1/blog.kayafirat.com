<template>
<div class="m-2 border flex flex-col">
  <table class="min-w-full divide-y divide-gray-200">
      <thead class="bg-gray-50">
      <tr>
          
          <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
          Tepki Süresi
          </th>
          <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
          Log Class
          </th>
          <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
          Log Method
          </th>
      </tr>
      </thead>
      <tbody class="bg-white divide-y divide-gray-200">
        <tr v-for="(log,index) in logs" :key="log">
          <ListItem v-bind:log="log" v-bind:index="index"/>
        </tr>
      </tbody>
  </table>
</div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import ListItem from './LogItem.vue'
export default {
  name:'LogList',
  components: { ListItem },
  methods:{
    ...mapGetters('log',['getAllLog']),
    ...mapActions('log',['getLogs'])
  },
  computed:{
    logs(){
      return this.getAllLog()
    }
  },
  created(){
    this.getLogs()
  }
}
</script>

<style>

</style>