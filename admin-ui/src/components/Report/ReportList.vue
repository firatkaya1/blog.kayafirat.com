<template>
<div>
  <div class="w-1/3 mx-auto">
    <BaseInput placeholder="Hata Raporlarında Ara" v-model="keyword"/>
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
               İsim 
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
          <tr v-for="report in users" :key="report"> 
            <ListItem v-bind:report="report"/>
          </tr>
        </tbody>
    </table>
  </div>
  <div class="h-48"></div>
</div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
import ListItem from './ReportListItem.vue'
export default {
  name:'UserList',
  components: { ListItem },
  data(){
    return {
      keyword:''
    }
  },
  
  methods:{
    ...mapActions('report',['getReports']),
    ...mapGetters('report',['getAllReport']),
  },
  computed:{
    users(){
      if(this.keyword.length > 0){
        return this.getAllReport() // .filter(u => u.username?.toLowerCase().includes(this.keyword?.toLowerCase())  ||  u.email?.toLowerCase().includes(this.keyword?.toLowerCase())  )
      }else {
        return this.getAllReport();
      }
     
    }
  },
  created(){
    this.getReports();
  }


}
</script>

<style>

</style>