<template>
    <td class="px-6 py-4 whitespace-nowrap">
        {{notification.username}}
    </td>
    <td class="px-6 py-4 whitespace-nowrap">
        <div class="text-sm text-gray-900 underline">{{notification.title}}</div>
    </td>
    <td class="px-6 py-4 whitespace-nowrap">
        <span class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-green-100 text-green-800">
            {{notification.created_Date}}
        </span>
    </td>
    <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
        {{notification.message}}
    </td>
    <td class="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
        <a href="#" class="text-red-600 hover:text-red-900 border rounded px-2 py-0.5 bg-red-50">Sil</a>
    </td>
</template>

<script>
export default {
    name:'NotificationListItem',
    props:{
        notification:{
            type:Object,
            required:true
        }
    }
}
</script>

<style>

</style>