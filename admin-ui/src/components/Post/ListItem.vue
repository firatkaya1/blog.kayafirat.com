<template>
    <td class="px-6 py-4 whitespace-nowrap">
        {{topic.postTitle}}
    </td>
    <td class="px-6 py-4 whitespace-nowrap">
        <div class="text-sm text-gray-900">{{topic.createdDate}}</div>
        <div class="text-sm text-gray-500">{{topic.updatedDate}}</div>
    </td>
    <td class="px-6 py-4 whitespace-nowrap text-center">
       {{topic.totalComment}}
    </td>
    <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500 text-center">
        {{topic.totalView}}
    </td>
    <td class="px-6 py-4 whitespace-nowrap text-sm text-black text-center">
        <span v-for="category in topic.categories" :key="category" class="bg-green-100 text-green-500 px-2 py-1 rounded-full mx-2">
            {{category}}
        </span>
    </td>
    <td class="px-6 py-4 whitespace-nowrap text-sm text-black text-center">
        <span class="bg-red-100 text-red-500 px-2 py-1 rounded-full mx-2 hover:bg-red-50 cursor-pointer" @click="$emit('delete',topic.postId)">
            Hızlı Sil
        </span>
    </td>
</template>

<script>
export default {
    name:'PostListItem',
    emits:['delete'],
    props:{
        topic:{
            type:Object,
            required:true
        }
    }
}
</script>

<style>

</style>