<template>
  <div class="w-full flex flex-col ">
    <div v-for="item in items" :key="item">
      <MenuItem v-bind:item="item"/>
    </div>
  </div>
</template>

<script>
import MenuItem from './MenuItem.vue'
export default {
  name:'Menu',
  components: { MenuItem },
  data(){
    return {
      items:[
        {
          name:"Kullanıcı Ayarları",
          link:"/home/user",
          icon:"UserAddIcon",
          children:[
            {
              name:"Kullanıcıları Listele",
              link:"/home/user/list",
              icon:"UserGroupIcon",
            }
          ]
        },
        {
          name:"Konu Ayarları",
          link:"/home/post",
          icon:"DocumentAddIcon",
          children:[
            {
              name:"Konuları Listele",
              link:"/home/post/list",
              icon:"DocumentDuplicateIcon",
            }
          ]
        },
        {
          name:"Yorum Ayarları",
          link:"/home/comment",
          icon:"ChatIcon",
          children:[
            {
              name:"Yorumları Listele",
              link:"/home/comment/list",
              icon:"ChatAlt2Icon",
            }
          ]
        },
        {
          name:"Bildirim Yolla",
          link:"/home/notification",
          icon:"BellIcon",
          children:[
            {
              name:"Bildirimleri Listele",
              link:"/home/notification/list",
              icon:"ChatAlt2Icon",
            }
          ]
        },
        {
          name:"Mail Yolla",
          link:"/home/mail",
          icon:"MailIcon",
          children:[
            {
              name:"Mail Listele",
              link:"/home/mail/list",
              icon:"ChatAlt2Icon",
            }
          ]
        },
        {
          name:"Resim Arşivi",
          link:"/home/image",
          icon:"PhotographIcon"
        },
        {
          name:"Çıkış",
          link:"/login",
          icon:"LogoutIcon"
        },
        
      ]
    }
  }
}
</script>

<style>

</style>