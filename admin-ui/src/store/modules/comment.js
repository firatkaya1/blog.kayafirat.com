import service from '../../service/service.js'

const state = {
    comments:[]
}

const getters = {
    getAllComments:state => state.comments
};

const actions = {
    getComments:({commit}) => {
        service.getAll("admin/comment/list")
        .then(response =>  {
            commit('SET_COMMENTS',response.data)
        })
    },
    
};

const mutations = {
    SET_COMMENTS  :(state,comments)=>state.comments = comments,
};

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
};