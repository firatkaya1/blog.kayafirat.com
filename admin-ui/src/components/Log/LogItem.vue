<template>
  <td class="px-6 py-4 whitespace-nowrap">
    {{log.log_Time}}
  </td>
  <td class="px-6 py-4 whitespace-nowrap">
    {{log.class_Name}}
  </td>
    <td class="px-6 py-4 whitespace-nowrap">
    {{log.method_Name}}
  </td>
</template>

<script>
export default {
    name:'LogItem',
    props:{
      log:{
        type:Object,
        required:true
      }
    }

}
</script>

<style>

</style>