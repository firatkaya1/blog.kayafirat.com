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
            Konu Adı ve Tarih
            </th>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
              Güncellenme Tarihi 
            </th>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
            Kısa Metin
            </th>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider text-right">
            
            </th>
        </tr>
        </thead>
        {{comments}}
        <tbody class="bg-white divide-y divide-gray-200">
          <tr v-for="comment in comments" :key="comment">
            <ListItem v-bind:comment="comment"/>
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