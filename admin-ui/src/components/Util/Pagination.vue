<template>
  <div class="flex flex-row">
    <div class="px-2 py-1 border rounded bg-white text-blue-500 hover:text-blue-700 w-8 text-center cursor-pointer bg-gray-50">
        <Icon name="ChevronDoubleLeftIcon" className="w-4 mt-1"  />
    </div>
    <div class="px-2 py-1 border bg-white text-blue-500 hover:text-blue-700 w-8 text-center cursor-pointer bg-gray-50">
        1
    </div>
    <div class="px-2 py-1 border bg-white text-blue-500 hover:text-blue-700 w-8 text-center cursor-pointer bg-gray-50">
        2
    </div>
    <div class="px-2 py-1 border bg-white text-blue-500 hover:text-blue-700 w-8 text-center cursor-pointer bg-gray-50">
        3
    </div>
    <div class="px-2 py-1 border bg-white text-blue-500 hover:text-blue-700 w-8 text-center cursor-pointer bg-gray-50">
        <Icon name="ChevronDoubleRightIcon" className="w-4 mt-1"  />
    </div>
  </div>
</template>

<script>
export default {
    name:'Pagination',
    props:{
        totalPage:{
            type:Number,
            required:false
        },
        currentPage:{
            type:Number,
            required:false
        }
    }

}
</script>

<style>

</style>