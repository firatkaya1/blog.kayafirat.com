<template>
<div class="w-1/3 mx-auto">
    <BaseInput placeholder="Konu Ara" v-model="keyword"/>
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
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
            Sil
            </th>
        </tr>
        </thead>
        <tbody class="bg-white divide-y divide-gray-200">
          <tr v-for="topic in topics" :key="topic">
            <ListItem v-bind:topic="topic" @delete="deletePost"/>
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
  data(){
    return {
      selectedTopic:null,
      keyword:''
    }
  },
  methods:{
    ...mapActions('topic',['getTopicsDetail']),
    ...mapGetters('topic',['getTopicsAllDetail']),
    deletePost(id){
      this.selectedTopic = this.getTopicsAllDetail().find(t => t.postId === id)
    }
  },
  computed:{
    topics(){
      if(this.keyword.length > 0){
        return this.getTopicsAllDetail().filter(t => t.postTitle?.toLowerCase().includes(this.keyword?.toLowerCase()));      
      } else {
        return this.getTopicsAllDetail();
      }
    }
  },
  created(){
    this.getTopicsDetail()
  }

}
</script>
