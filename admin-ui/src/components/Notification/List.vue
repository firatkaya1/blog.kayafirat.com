<template>
<div class="w-1/3 mx-auto">
    <BaseInput placeholder="Yorum Ara"/>
</div>
  <div class="m-2 border flex flex-col">
    <table class="min-w-full divide-y divide-gray-200">
        <thead class="bg-gray-50">
        <tr>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
            Kullanıcı Adı
            </th>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
              Bildirim Başlığı
            </th>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
              Oluşturulma Tarihi
            </th>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
            Detay
            </th>
        </tr>
        </thead>
        <tbody class="bg-white divide-y divide-gray-200">
        <tr v-for="notification in notifications" :key="notification">
          <ListItem v-bind:notification="notification"/>
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
import { mapActions, mapGetters } from 'vuex'
import ListItem from './ListItem.vue'
export default {
  name:'NotificationList',
  components: { ListItem },
  methods:{
    ...mapGetters('notification',['getAllNotification']),
    ...mapActions('notification',['getNotifications'])
  },
  computed:{
    notifications(){
      return this.getAllNotification()?.content;
    }
  },
  created(){
    this.getNotifications();
  }

}
</script>

<style>

</style>