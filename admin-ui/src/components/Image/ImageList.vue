<template>
  <div class="flex flex-col">
    <div class="w-1/3 mx-auto">
      <BaseInput placeholder="Arama yap..." />
    </div>
    <div class="my-4 text-sm ml-4">
      Resim arşivine hoşgeldin. Görüntülemek istediğin resme tıklarsan büyük boyuta geçersin.
    </div>
    <div class="flex flex-row flex-wrap">
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
      <ImageItem />
    </div>
    <div class="mx-auto mt-12">
      <Pagination />
    </div>
  </div>
</template>

<script>
import ImageItem from './ImageItem.vue'

export default {
  components: { ImageItem },

}
</script>

<style>

</style>