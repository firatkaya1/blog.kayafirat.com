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
                router.push("/home");
                    commit('setLoggedUser',response.data)

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
            })
        })
    },
    updateUser:({commit},body) => {
        service.update("admin/user",body)
        .then(response =>  {
            commit('SET_USER',response.data)
        })
    },
    deleteUser:({commit},id) => {
        service.delete("admin/user/",id)
        .then(() =>  {
            commit('DELETE_USER',id)
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