<template>
      <component :is="heroIcons[name]" :class="className" />
</template>

<script>
import * as heroIconsOutline from '@heroicons/vue/outline'
import * as heroIconsSolid from '@heroicons/vue/solid'
export default {
    name:"HeroIcon",
    props:{
        name:{
            type:String,
            required:true,
            
        },
        className:{
            type:String,
            default:'default-size'
        },
        type:{
            type:String,
            default:"outline"
        }
    },
    data(){
        return {
            icon:"",
            heroIcons : (this.type=='outline') ? heroIconsOutline : heroIconsSolid 
        }
    }
   
}
</script>

<style scoped>
.default-size {
  width:24px;
  height: 24px;
}
</style>