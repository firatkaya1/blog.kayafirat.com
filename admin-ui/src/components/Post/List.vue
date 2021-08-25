<template>
<div class="w-1/3 mx-auto">
    <BaseInput placeholder="Kullanıcı Ara"/>
</div>
  <div class="m-2 border flex flex-col">
    <table class="min-w-full divide-y divide-gray-200">
        <thead class="bg-gray-50">
        <tr>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
            Resmi
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
        <ListItem/>
        <ListItem/>
        <ListItem/>
        <ListItem/>
        <ListItem/>
        <ListItem/>
        <ListItem/>
        <ListItem/>
        <ListItem/>
        <ListItem/>
        <ListItem/>
        <ListItem/>
        <ListItem/>
        <ListItem/>
        <ListItem/>
        <ListItem/>
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

export default {
  components: { ListItem },

}
</script>

<style>

</style>