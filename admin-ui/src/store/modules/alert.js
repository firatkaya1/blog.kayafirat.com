const state = {
    alerts:[]
}

const getters = {
    getAlerts:state => state.alerts
};

const actions = {

};

const mutations = {
    pushNotification(state,notify){
        notify.id = (Math.floor(Math.random() * 9999) +1) /* Rastgele bir id vermemiz gerekiyor ki kullanıcı sonra dan istediği bir notification'ı ID'ye göre silebilsin.*/
        state.alerts.push(notify);
    },
    deleteNotification(state,id){
        state.alerts = state.alerts.filter((item) => item.id !== id);
    }
};

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
};