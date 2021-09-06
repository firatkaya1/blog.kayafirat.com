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
    updateComment:({commit},data) => {
        service.update('admin/comment',data.body)
            .then(() => {
                commit('UPDATE_COMMIT',data)
            })
    }
};

const mutations = {
    SET_COMMENTS  :(state,comments)=>state.comments = comments,
    UPDATE_COMMIT :(state,data) => {
        var index = state.comments.findIndex(c => c.commentId == data.body.id)
        state.comments[index].commentBody = data.body.body
        state.comments[index].commentIsdelete = data.body.isDelete
        state.comments[index].commentIsHide = data.body.isHide
    }
};

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
};