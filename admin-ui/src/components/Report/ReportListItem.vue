<template>
    <td class="px-6 py-4 whitespace-nowrap">
       {{report.reportId}}
    </td>
    <td class="px-6 py-4 whitespace-nowrap">
       {{report.reportCode}}
    </td>
    <td class="px-6 py-4 whitespace-nowrap">
       {{report.name}}
    </td>
    <td class="px-6 py-4 whitespace-nowrap">
        {{report.email}}
    </td>
    <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
        {{report.reportMessage}}
    </td>
    <td class="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
        <div class="text-red-600 hover:text-red-900 border rounded px-2 py-0.5 bg-red-50 text-center" @click="removeReport">Sil</div>
    </td>
</template>

<script>
import { mapActions } from 'vuex'
export default {
    name:'UserListItem',
    props:{
        report:{
            type:Object,
            required:true
        }
    },
    methods:{
        ...mapActions('report',['deleteReport']),
        removeReport(){
            this.deleteReport(this.report.reportId)
        }
    }
}
</script>

<style>

</style>