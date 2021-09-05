<template>
<div class="w-1/3 mx-auto">
    <BaseInput placeholder="Kullanıcı Ara"/>
</div>
  <div class="m-2 border flex flex-col">
    <table class="min-w-full divide-y divide-gray-200">
        <thead class="bg-gray-50">
        <tr>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
            ID-Başlık
            </th>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
            Oluşturulma ve Güncellenme Tarihi
            </th>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
            Toplam Yorum
            </th>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
            Toplam Görüntülenme
            </th>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
            Kategoriler
            </th>
        </tr>
        </thead>
        <tbody class="bg-white divide-y divide-gray-200">
          <tr v-for="topic in topics" :key="topic">
            <ListItem v-bind:topic="topic"/>
          </tr>
        </tbody>
    </table>
  </div>
  <div class="h-48"></div>

</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import ListItem from './ListItem.vue'

export default {
  name:'PostList',
  components: { ListItem },
  methods:{
    ...mapActions('topic',['getTopicsDetail']),
    ...mapGetters('topic',['getTopicsAllDetail']),
  },
  computed:{
    topics(){
      return this.getTopicsAllDetail();
    }
  },
  created(){
    this.getTopicsDetail()
  }

}
</script>

<style>

</style>