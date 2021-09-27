<template>
<td class="px-6 py-4 whitespace-nowrap">
        <div class="flex items-center">
            <div class="ml-4">
            <div class="text-sm font-medium text-gray-900">
                {{user.username}}
            </div>
            <div class="text-sm text-gray-500">
                {{user.email}}
            </div>
            </div>
        </div>
    </td>
    <td class="px-6 py-4 whitespace-nowrap">
        <div class="text-sm text-gray-900">{{user.createdDate}}</div>
        <div class="text-sm text-gray-500"></div>
    </td>
    <td class="px-6 py-4 whitespace-nowrap">
        <span class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-green-100 text-green-800" v-if="user.accountStatus">
            active
        </span>
         <span class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-red-100 text-red-800" v-if="!user.accountStatus">
            passive
        </span>
    </td>
    <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
        <span v-for="role in user.role" :key="role">{{role.roleName}} <br> </span>
    </td>
    <td class="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
        <div class="text-red-600 hover:text-red-900 border rounded px-2 py-0.5 bg-red-50 text-center" @click="removeUser">Sil</div>
    </td>
</template>

<script>
import { mapActions } from 'vuex'
export default {
    name:'UserListItem',
    props:{
        user:{
            type:Object,
            required:true
        }
    },
    methods:{
        ...mapActions('user',['deleteUser']),
        removeUser(){
            this.deleteUser(this.user.userId)
        }
    }
}
</script>

<style>

</style>