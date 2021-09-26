<template>
  <div class="flex h-screen px-4 w-full justify-between">
    <div class="flex flex-col space-y-4">
      <label for="users">Select user: </label>
      <select name="users" id="users" class="bg-white border px-2 py-1 focus:outline-none focus:ring-2 rounded" v-model="selectedUserId">
        <option :value="null">Kullanıcı seçiniz</option>
        <option :value="s.userId" v-for="s in users" :key="s"> {{s.username}} </option>
      </select>
      <BaseInput id="email" label="Email Address" placeholder="Email Address" v-model="selectedUser.email" />
      <BaseInput id="username" label="Username" placeholder="Username" v-model="selectedUser.username"/>
      <BaseInput id="password" label="Password" placeholder="Password" v-model="selectedUser.password"/>
      <BaseInput id="registerDate" label="Register Date" placeholder="Register Address" v-model="selectedUser.userProfile.birthDate"/>
      <BaseInput id="contactAddress" label="Contact Address" placeholder="Contact Address" v-model="selectedUser.userProfile.contactAddress"/>
      <BaseInput id="githubAddress" label="Github Address" placeholder="Github Address" v-model="selectedUser.userProfile.githubAddress"/>
      <BaseInput id="linkedinAddress" label="Linkedin Address" placeholder="Linkedin Address" v-model="selectedUser.userProfile.linkedinAddress"/>
      <BaseCheckbox id="999" label="Hesap aktif mi ?" v-model="selectedUser.userProfile.accountStatus"/>
      
    </div>
    <div class="flex flex-col space-y-2">
      <h1 class="font-bold text-xl">Mail Ayarları</h1>
      <BaseCheckbox id="1" label="Hesabıma 3'den fazla deneme yapıldığında bana mail gelmesini istiyorum." v-model="selectedUser.mailPermission.loginAttempt" :checked="selectedUser.mailPermission.loginAttempt"/>
      <BaseCheckbox id="2" label="Her giriş yapıldığında mail almak istiyorum." v-model="selectedUser.mailPermission.loginNotification" :checked="selectedUser.mailPermission.loginNotification"/>
      <BaseCheckbox id="3" label="Yeni bir yazı eklendiğinde mail almak istiyorum." v-model="selectedUser.mailPermission.postNotification"  :checked="selectedUser.mailPermission.postNotification"/>
      <BaseCheckbox id="4" label="Şifre değişikliğinde mail almak istiyorum." v-model="selectedUser.mailPermission.passChange" :checked="selectedUser.mailPermission.passChange"/>
      <h1 class="font-bold text-xl mt-4">Bildirim Ayarları</h1>
      <BaseCheckbox id="5" label="Hesabıma 3'den fazla deneme yapıldığında bana bildirilmesini istiyorum." v-model="selectedUser.notificationPermission.loginAttempt" :checked="selectedUser.notificationPermission.loginAttempt"/>
      <BaseCheckbox id="6" label="Her giriş yapıldığında bildirim almak istiyorum." v-model="selectedUser.notificationPermission.loginNotification" :checked="selectedUser.notificationPermission.loginNotification"/>
      <BaseCheckbox id="7" label="Yeni bir yazı eklendiğinde bildirim almak istiyorum." v-model="selectedUser.notificationPermission.postNotification" :checked="selectedUser.notificationPermission.postNotification"/>
      <BaseCheckbox id="8" label="Şifre değişikliğinde bildirim almak istiyorum." v-model="selectedUser.notificationPermission.passChange" :checked="selectedUser.notificationPermission.passChange"/>
      <h1 class="font-bold text-xl mt-4">Hesap Ayarları</h1>
      <BaseCheckbox id="9" label="İletişim adresimin görünmesini istemiyorum." v-model="selectedUser.userPermission.contact" :checked="selectedUser.userPermission.contact"/>
      <BaseCheckbox id="10" label="Doğum tarihimin görünmesini istemiyorum." v-model="selectedUser.userPermission.birthdate" :checked="selectedUser.userPermission.birthdate"/>
      <BaseCheckbox id="11" label="Github adresimin görünmesini istemiyorum." v-model="selectedUser.userPermission.github" :checked="selectedUser.userPermission.github"/>
      <BaseCheckbox id="12" label="Linkedin adresimin görünmesini istemiyorum." v-model="selectedUser.userPermission.linkedin" :checked="selectedUser.userPermission.linkedin"/>

      <h1 class="font-bold text-xl mt-4">Güvenlik Ayarları</h1>
      <BaseCheckbox id="13" label="Hesabı aktive et." v-model="selectedUser.enabled" :checked="selectedUser.enabled"/>
      <BaseCheckbox id="14" label="Hesap süresi dolmuş." v-model="selectedUser.accountExpired" :checked="selectedUser.accountExpired"/>
      <BaseCheckbox id="15" label="Hesabı kilitle." v-model="selectedUser.accountLocked" :checked="selectedUser.accountLocked"/>
      <BaseCheckbox id="16" label="Kullanıcı şifresinin süresi dolmuş." v-model="selectedUser.passwordExpired" :checked="selectedUser.passwordExpired"/>


      <div class="flex flex-row justify-between text-white w-auto  text-sm px-2 py-0.5 rounded cursor-pointer" :class="{'bg-blue-500':!hasAdmin,'bg-green-500':hasAdmin}" @click="updateRole(1,'ROLE_ADMIN')">
        <Icon :name="(hasAdmin) ? 'CheckIcon' : 'XIcon'" class="cursor-pointer hover:text-gray-300" /> ROLE_ADMIN
      </div>
      <div class="flex flex-row justify-between text-white w-auto  text-sm px-2 py-0.5 rounded cursor-pointer" :class="{'bg-blue-500':!hasUser,'bg-green-500':hasUser}" @click="updateRole(2,'ROLE_USER')">
        <Icon :name="(hasUser) ? 'CheckIcon' : 'XIcon'" class="cursor-pointer hover:text-gray-300" /> ROLE_USER
      </div>
    </div>

    <div class="flex flex-col">
      <img src="@/assets/logo.png" class="h-36 w-36" alt="">
      <button class="bg-gray-100 px-2 py-1 text-sm rounded border hover:bg-gray-50 hover:text-gray-700 focus:ring-2 transition duration-300 mt-4"> Profil Resmini Değiştir</button> 
      <button class="bg-green-500 px-2 py-2 text-sm rounded-md border hover:bg-green-600 focus:ring-2 transition duration-300 mt-4 text-white" v-if="selectedUserId == null" @click="addUser"> Yeni Kullanıcı Kaydet </button>
      <button class="bg-blue-500 px-2 py-2 text-sm rounded-md border hover:bg-blue-600 focus:ring-2 transition duration-300 mt-4 text-white" @click="userUpdate" v-if="selectedUser.id != null"> Değişiklikleri Kaydet </button>
      <button class="bg-red-600 px-2 py-2 text-sm rounded-md border hover:bg-red-700 focus:ring-2 transition duration-300 mt-4 text-white" @click="removeUser" v-if="selectedUser.id != null"> Seçili Kullanıcıyı Sil </button>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
export default {
  name:'UserAdd',
  data(){
    return {
      selectedUserId:null,
      selectedUser:{
        id: null,
        email: null,
        username: null,
        password: null,
        enabled : null,
        accountExpired:null,
        accountLocked:null,
        passwordExpired:null,
        role: [],
        userPermission:{},
        userProfile:{},
        notificationPermission:{},
        mailPermission: {}
      }
      
    }
  },
  methods:{
    ...mapGetters('user',['getAllUsers','getUser']),
    ...mapActions('user',['getUserById','getUsers','updateUser','deleteUser','saveUser']),
    userUpdate(){
      this.updateUser(this.selectedUser)
    },
    updateRole(id,val){
      if(this.selectedUser.id != null){
        var index = this.selectedUser.role.findIndex(r => r.id == id)
        if(index != -1){
          this.selectedUser.role.splice(index,1)
        } else {
          this.selectedUser.role.push({id:id,role:val})
        }
      }
    },
    removeUser(){
      this.deleteUser(this.selectedUser.id)
    },
    addUser(){
      var body = {
        username : this.selectedUser.username,
        email : this.selectedUser.email,
        password : this.selectedUser.password
      }
      this.saveUser(body)
      this.clear();
    },
    clear(){
      this.selectedUser.username = null
      this.selectedUser.email = null
      this.selectedUser.password = null

    }
  },
  computed:{
    users(){
      return this.getAllUsers()
    },
    user(){
      return this.getUser()
    },
    hasAdmin(){
     return (this.selectedUser.role.findIndex(r => r.id == 1) != -1 ) ? true : false
    },
    hasUser(){
     return (this.selectedUser.role.findIndex(r => r.id == 2) != -1 ) ? true : false
    }
  },
  watch:{
    selectedUserId(newVal){
      this.getUserById(newVal)
    },
    user(newVal){
      this.selectedUser = newVal
    }
  },
  created(){
    this.getUsers();
  }
}
</script>

<style>

</style>