<template>
    <td class="px-6 py-4 whitespace-nowrap">
       {{contact.contactId}}
    </td>
    <td class="px-6 py-4 whitespace-nowrap">
       {{contact.contactCode}}
    </td>
    <td class="px-6 py-4 whitespace-nowrap">
        {{contact.email}}
    </td>
    <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
        {{contact.contactMessage}}
    </td>
    <td class="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
        <div class="text-red-600 hover:text-red-900 border rounded px-2 py-0.5 bg-red-50 text-center" @click="removeContact">Sil</div>
    </td>
</template>

<script>
import { mapActions } from 'vuex'
export default {
    name:'ContactListItem',
    props:{
        contact:{
            type:Object,
            required:true
        }
    },
    methods:{
        ...mapActions('contact',['deleteContact']),
        removeContact(){
            this.deleteContact(this.contact.contactId)
        }
    }
}
</script>

<style>

</style>