<template>
  <div class="flex h-screen px-4 justify-between">
    <div class="flex flex-col w-1/3 space-y-4">
      <label for="types">Bir mail templatei seçiniz. {{selectedType}} </label>
      <select name="types" id="types" class="bg-white border px-2 py-1 focus:outline-none focus:ring-2 rounded" v-model="selectedType">
        <option selected>Bir mail tipi seçiniz.</option>
        <option value="Verification">Verification</option>
        <option value="VerificationSuccess">VerificationSuccess</option>
        <option value="PasswordChanged">PasswordChanged</option>
        <option value="PasswordChangedSuccess">PasswordChangedSuccess</option>
        <option value="LoginSuccess">LoginSuccess</option>
        <option value="LoginAttempt">LoginAttempt</option>
        <option value="Other">Other</option>
      </select>
      <select name="users" id="users" class="bg-white border px-2 py-1 focus:outline-none focus:ring-2 rounded" v-model="selectedUser" :disabled="sendAll">
        <option :value="null">Kullanıcı seçiniz</option>
        <option :value="s" v-for="s in users" :key="s"> {{s.username}} </option>
      </select>
      <BaseCheckbox id="sendAll" label="Herkese gönder." v-model="sendAll" :checked="sendAll" />
      <BaseInput id="notificationTitle" label="Başlık" v-model="mailTitle" placeholder="Mail başlığı giriniz." :show="selectedType == 'Other'" />
      <BaseInput id="notificationTitle" label="Alt Başlık" v-model="mailsubtitle" placeholder="Alt Başlığı giriniz." :show="selectedType == 'Other'" />
      <BaseTextarea id="notificationBody" v-model="mailBody" placeholder="Mesajınızı giriniz." :show="selectedType == 'Other'" />
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
      <button class="bg-green-100 px-2 py-1 text-sm rounded border hover:bg-green-50 hover:text-green-700 focus:ring-2 transition duration-300 mt-4" @click="saveMail"> Bildirimleri Gönder </button> 
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import service from "@/service/service.js"
export default {
  name:'Mail',
  data(){
    return {
      selectedType:null,
      selectedUser:null,
      sendAll:false,
      selectedUsers:[],
      mailTitle:null,
      mailsubtitle:null,
      mailBody:null
    }
  },
  methods:{
    ...mapGetters('user',['getAllUsers']),
    ...mapActions('user',['getUsers','getUserById']),
    removeUser(userId){
      this.selectedUsers = this.selectedUsers.filter(u => u.userId != userId)
    },
    saveMail(){
      const body = {
        type:this.selectedType,
        mailTitle:this.mailTitle,
        mailsubtitle:this.mailsubtitle,
        mailBody:this.mailBody,
        userIds:this.selectedUsers.map( u => u.userId),
        sendAll:this.sendAll
      }
      service.save("admin/mail",body).then(() =>  this.clear())
    },
    clear(){
      this.selectedType=null,
      this.selectedUser=null,
      this.sendAll=false,
      this.selectedUsers=[],
      this.mailTitle=null,
      this.mailsubtitle=null,
      this.mailBody=null
    }
  },
  computed:{
    users(){
      return this.getAllUsers()
    }
  },
  watch:{
    user(newVal){
      this.selectedUser = newVal
    },
    selectedUser(newVal){
      if(this.selectedUsers.findIndex(element => element.userId == newVal.userId) == -1 ){
        this.selectedUsers.push(newVal)
      }
    }
  },
  created(){
    this.getUsers();

  }
}
</script>

<style>

</style>