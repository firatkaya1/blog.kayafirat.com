<template>
  <div class="container w-10/12 ">
    <h1 class="font-bold text-2xl"> Ayarlar </h1>
    <div class="flex flex-row justify-center space-x-12">
      <button class="p-2 border focus:outline-0 bg-green-500 text-gray-700 hover:bg-green-400 rounded text-sm" @click="clearCache"> Cache'i Temizle </button>
      <button class="p-2 border focus:outline-0 bg-blue-500 text-white hover:bg-blue-400 rounded text-sm" @click="updateSiteStatus"> Siteyi Statusu Güncelle -> {{siteStatus}} </button>
    </div>
  </div>
</template>

<script>
import { mapMutations } from 'vuex'
import service from '../../service/service'
export default {
  name:'Settings',
  data(){
    return {
      siteStatus:true
    }
  },
  methods:{
    ...mapMutations('alert',['pushAlert']),
    clearCache(){
      service.save('admin/config/cache')
        .then(() => {
          this.pushAlert({message:"Cache başarıyla temizlendi",type:'success'})
        })
        .catch(() => {
          this.pushAlert({message:"Cache temizlenirken bir hata meydana geldi.",type:'error'})
        })
    },
    updateSiteStatus() {
      service.save('admin/config',{configId:1,configValue:!this.siteStatus,configKod:'isUp'})
        .then((response) => {
          this.siteStatus = response.data.configValue == "true"
          this.pushAlert({message:"Durum güncellendi.",type:'success'})
        })
        .catch(() => {
          this.pushAlert({message:"Güncellenirken bir hata meydana geldi.",type:'error'})
        })
    }
  }
}
</script>

<style>

</style>