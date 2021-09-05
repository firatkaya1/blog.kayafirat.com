<template>
<div class="w-1/3 mx-auto">
    <BaseInput placeholder="Maillerde Ara"/>
</div>
  <div class="m-2 border flex flex-col">
    <table class="min-w-full divide-y divide-gray-200">
        <thead class="bg-gray-50">
        <tr>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
            Email
            </th>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
            Oluşturulma ve Gönderilme Tarih
            </th>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
              Tip
            </th>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
            Durum
            </th>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
            Başlık
            </th>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
            Alt Başlık
            </th>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
            Mesaj
            </th>
        </tr>
        </thead>
        <tbody class="bg-white divide-y divide-gray-200">
          <tr v-for="mail in mails" :key="mail">
            <ListItem v-bind:mail="mail"/>
          </tr>
        </tbody>
    </table>
  </div>
  <div class="flex justify-end text-center mx-auto ">
      <Pagination />
  </div>
  <div class="h-48"></div>
</template>

<script>
import ListItem from './ListItem.vue'
import service from '@/service/service.js'
export default {
  name:'MailList',
  components: { ListItem },
  data(){
    return { 
      mailList:[]
    }
  },
  computed:{
    mails(){
      return this.mailList
    }
  },
  created(){
    service.getAll("admin/mail/list").then(response => { this.mailList = response.data })
  }

}
</script>

<style>

</style>