<template>
  <div class="flex h-screen px-4 justify-between">
    <div class="flex flex-col w-1/3 space-y-4">
      <select name="users" id="users" class="bg-white border px-2 py-1 focus:outline-none focus:ring-2 rounded" v-model="selectedUser" :disabled="sendAll">
        <option :value="null">Kullanıcı seçiniz</option>
        <option :value="s" v-for="s in users" :key="s"> {{s.username}} </option>
      </select>
      <BaseCheckbox id="sendAll" label="Herkese gönder." v-model="sendAll" :checked="sendAll" />
      <BaseInput id="notificationIcon" label="Icon" placeholder="Bir icon adı giriniz." v-model="notificationIcon" />
      <BaseInput id="notificationTitle" label="Başlık" placeholder="Bildirim başlığı giriniz." v-model="notificationTitle"/>
      <BaseTextarea placeholder="Bildirim Detayı giriniz." v-model="notificationBody"/>
      <BaseInput id="link" label="Link" placeholder="Bildirime tıklanınca gidecek adresi giriniz." v-model="notificationLink"/>
    </div>
    <div class="flex flex-wrap  mt-4">
          <div class="flex px-2 py-1 rounded-full text-sm mx-1 my-1 bg-blue-500 h-7 text-white"  v-for="user in selectedUsers" :key="user"> 
            {{user.username}} 
            <span @click="removeUser(user.userId)">
              <Icon name="XIcon" className="h-4 w-4 ml-4 cursor-pointer hover:text-gray-500"/>
            </span>
          </div>
      </div>
    <div class="flex flex-col">
      <button class="bg-gray-100 px-2 py-1 text-sm rounded border hover:bg-gray-50 hover:text-gray-700 focus:ring-2 transition duration-300 mt-4" @click="clear"> Temizle </button> 
      <button class="bg-green-100 px-2 py-1 text-sm rounded border hover:bg-green-50 hover:text-green-700 focus:ring-2 transition duration-300 mt-4" @click="sendNotification"> Bildirimleri Gönder </button> 
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
export default {
  name:'Notification',
  data(){
    return {
      selectedUser:null,
      selectedUsers:[],
      sendAll:false,
      notificationIcon:'',
      notificationTitle:'',
      notificationBody:'',
      notificationLink:'',
    }
  },
  methods:{
    ...mapGetters('user',['getAllUsers','getUser']),
    ...mapActions('user',['getUsers']),
    removeUser(userId){
      this.selectedUsers = this.selectedUsers.filter(u => u.userId != userId)
    },
    clear(){
      this.notificationIcon=''
      this.notificationTitle=''
      this.notificationBody=''
      this.notificationLink=''
      this.selectedUser = null
      this.sendAll = false
    },
    sendNotification(){

    }
  },
  computed:{
    users(){
      return this.getAllUsers()
    }
  },
  created(){
    this.getUsers();
  },
  watch:{
    selectedUser(newVal){
      if(this.selectedUsers.findIndex(element => element.userId == newVal.userId) == -1 ){
        this.selectedUsers.push(newVal)
      }
    }
  }
}
</script>

<style>

</style>