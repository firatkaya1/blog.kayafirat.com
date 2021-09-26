import service from '../../service/service.js'

const state = {
    notifications:[]

}

const getters = {
    getAllNotification:state => state.notifications

};

const actions = {
    getNotifications:({commit}) => {
        service.getAll("admin/notification/list")
        .then(response =>  {
            commit('SET_NOTIFICATIONS',response.data)

        })
    },
    saveNotification:({commit},body) => {
        service.save("admin/notification",body)
        .then(response =>  {
            commit('ADD_NOTIFICATION',response.data)
            commit('alert/pushAlert',{message:"Yeni bildirim yollandı.",type:'success'},{root:true})

        })
    }

};

const mutations = {
    SET_NOTIFICATIONS :(state,notifications)=>state.notifications = notifications,
    ADD_NOTIFICATION:(state,notification) => state.notifications.push(notification)
};

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
};