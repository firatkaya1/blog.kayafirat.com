<template>
<div class="w-1/3 mx-auto">
    <BaseInput placeholder="Yorum Ara"/>
</div>
  <div class="m-2 border flex flex-col">
    <table class="min-w-full divide-y divide-gray-200">
        <thead class="bg-gray-50">
        <tr>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
            ID
            </th>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
            Kullanıcı Adı
            </th>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
              Oluşturulma Tarihi ve Detay
            </th>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
            Silinme Durumu
            </th>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
            Gizlenme Durumu
            </th>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
            Toplam Oy
            </th>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
            
            </th>
        </tr>
        </thead>
        <tbody class="bg-white divide-y divide-gray-200">
          <tr v-for="(comment,index) in comments" :key="comment">
            <ListItem v-bind:comment="comment" v-bind:index="index"/>
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
  name:'CommentList',
  components: { ListItem },
  methods:{
    ...mapGetters('comment',['getAllComments']),
    ...mapActions('comment',['getComments'])
  },
  computed:{
    comments(){
      return this.getAllComments().content
    }
  },
  created(){
    this.getComments()
  }


}
</script>

<style>

</style>