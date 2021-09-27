import service from '../../service/service.js'

const state = {
    logs:[]

}

const getters = {
    getAllLog:state => state.logs

};

const actions = {
    getLogs:({commit}) => {
        service.getAll("admin/log/list")
        .then(response =>  {
            commit('SET_LOGS',response.data)

        })
    }

};

const mutations = {
    SET_LOGS :(state,logs)=>state.logs = logs,
};

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
};