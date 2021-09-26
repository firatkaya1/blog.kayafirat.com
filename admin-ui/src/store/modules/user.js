import service from '../../service/service.js'
import router from "../../router/index.js"

const state = {
    loggedUser:{},
    users:[],
    user:{}

}

const getters = {
    getLoggedUser:state=> state.loggedUser,
    getAllUsers:state => state.users,
    getUser:state => state.user

};

const actions = {
    loggedUser:({commit}) => {
        service.getCurrentUser()
            .then(response =>  {
                commit('alert/pushAlert',{message:"Başarılı bir şekilde giriş yapıldı.",type:'success'},{root:true})
                router.push("/home");
                    commit('setLoggedUser',response.data)
            })
            .catch(error => {
                commit('alert/pushAlert',{message:"Bir hata ile karşılaşıldı:"+error,type:'error'},{root:true})
            })
    },
    getUsers:({commit}) => {
        service.getAll("admin/user/list")
        .then(response =>  {
            commit('SET_USERS',response.data)
        })
    },
    getUserById:({commit},id) => {
        service.getAll("admin/user/"+id)
        .then(response =>  {
            commit('SET_USER',response.data)

        })
    },
    saveUser:({commit},body) => {
        service.save("admin/user",body)
        .then(() =>  {
            service.getAll("admin/user/list")
            .then(response =>  {
                commit('SET_USERS',response.data)
                commit('alert/pushAlert',{message:"Yeni bir kullanıcı kayıt edildi.",type:'success'},{root:true})

            })
        })
    },
    updateUser:({commit},body) => {
        service.update("admin/user",body)
        .then(response =>  {
            commit('SET_USER',response.data)
            commit('alert/pushAlert',{message:"Mevcut kullanıcı güncellendi.",type:'success'},{root:true})

        })
    },
    deleteUser:({commit},id) => {
        service.delete("admin/user/",id)
        .then(() =>  {
            commit('DELETE_USER',id)
            commit('alert/pushAlert',{message:"Bir kullanıcı silindi.",type:'success'},{root:true})
        })
    },

};

const mutations = {
    setLoggedUser  :(state,loggedUser) => state.loggedUser = loggedUser,
    SET_USERS      :(state,users) => state.users = users,
    SET_USER      :(state,user) => state.user = user,
    DELETE_USER      :(state,id) => {
        state.users = state.users.filter(u => u.userId != id)
        state.user = {}
    }


};

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
};