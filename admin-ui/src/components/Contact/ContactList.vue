<template>
<div>
  <div class="w-1/3 mx-auto">
    <BaseInput placeholder="İletişimlerde Ara" v-model="keyword"/>
</div>
  <div class="m-2 border flex flex-col">
    <table class="min-w-full divide-y divide-gray-200">
        <thead class="bg-gray-50">
        <tr>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                ID
            </th>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Kod
            </th>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Email
            </th>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Mesaj
            </th>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider text-right">
                Sil
            </th>
        </tr>
        </thead>
        <tbody class="bg-white divide-y divide-gray-200">
          <tr v-for="contact in contacts" :key="contact"> 
            <ListItem v-bind:contact="contact"/>
          </tr>
        </tbody>
    </table>
  </div>
  <div class="h-48"></div>
</div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
import ListItem from './ContactListItem.vue'
export default {
  name:'ContactList',
  components: { ListItem },
  data(){
    return {
      keyword:''
    }
  },
  
  methods:{
    ...mapActions('contact',['getContacts']),
    ...mapGetters('contact',['getAllContact']),
  },
  computed:{
    contacts(){
      if(this.keyword.length > 0){
        return this.getAllContact() // .filter(u => u.username?.toLowerCase().includes(this.keyword?.toLowerCase())  ||  u.email?.toLowerCase().includes(this.keyword?.toLowerCase())  )
      }else {
        return this.getAllContact();
      }
     
    }
  },
  created(){
    this.getContacts();
  }


}
</script>

<style>

</style>