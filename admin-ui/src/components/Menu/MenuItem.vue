<template>
    <router-link :to="item.link" >
        <div class="flex px-2 py-3 hover:bg-gray-100 cursor-pointer border" @click="isOpen = !isOpen">
            <icon :name="item.icon" className="h-6 w-6 mr-4" />
            {{item.name}}
        </div>
        <div v-if="isOpen && item.children">
            <div v-for="children in item.children" :key="children" class="px-4">
                <MenuItem v-bind:item="children"/>
            </div>
        </div>
    </router-link>
</template>

<script>
export default {
    name:'MenuItem',
    props:{
        item:{
            type:Object,
            required:true
        }
    },
    data(){
        return {
            isOpen:false
        }
    }

}
</script>

<style>

</style>