<template>
<div>
  <div class="w-1/3 mx-auto">
    <BaseInput placeholder="Kullanıcı Ara" v-model="keyword"/>
</div>
  <div class="m-2 border flex flex-col">
    <table class="min-w-full divide-y divide-gray-200">
        <thead class="bg-gray-50">
        <tr>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
            Kullanıcı Adı ve Email
            </th>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
            Üyelik Tarihi ve Son Giriş
            </th>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
            Onay Durumu
            </th>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
            Role
            </th>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider text-right">
            Sil
            </th>
        </tr>
        </thead>
        <tbody class="bg-white divide-y divide-gray-200">
          <tr v-for="user in users" :key="user"> 
            <ListItem v-bind:user="user"/>
          </tr>
        </tbody>
    </table>
  </div>
  <div class="h-48"></div>
</div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
import ListItem from './ListItem.vue'
export default {
  name:'UserList',
  data(){
    return {
      keyword:''
    }
  },
  components: { ListItem },
  methods:{
    ...mapActions('user',['getUsers']),
    ...mapGetters('user',['getAllUsers']),
  },
  computed:{
    users(){
      if(this.keyword.length > 0){
        return this.getAllUsers().filter(u => u.username?.toLowerCase().includes(this.keyword?.toLowerCase())  ||  u.email?.toLowerCase().includes(this.keyword?.toLowerCase())  )
      }else {
        return this.getAllUsers();
      }
     
    }
  },
  created(){
    this.getUsers();
  }


}
</script>

<style>

</style>