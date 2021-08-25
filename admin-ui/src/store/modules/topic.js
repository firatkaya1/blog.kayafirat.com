import service from '../../service/service.js'

const state = {
    topics:[],
    topic:{},
    categories:[]

}

const getters = {
    getAllTopic:state => state.topics,
    getTopic:state => state.topic,
    getAllCategories:state => state.categories

};

const actions = {
    getTopics:({commit}) => {
        service.getAll("admin/topic/list")
        .then(response =>  {
            commit('SET_TOPICS',response.data)
        })
    },
    getTopicById:({commit},id) => {
        service.getAll("admin/topic/"+id)
        .then(response =>  {
            commit('SET_TOPIC',response.data)

        })
    },
    getCategories:({commit}) => {
        service.getAll("category")
        .then(response =>  {
            commit('SET_CATEGORIES',response.data)

        })
    },
    saveTopic:({commit},body) => {
        console.log(body)
        service.save("post",body)
        .then(() =>  {
            commit('SET_TOPICS',{})
        })
    }

};

const mutations = {
    SET_TOPICS      :(state,topics) => state.topics = topics,
    SET_TOPIC       :(state,topic)  => state.topic = topic,
    SET_CATEGORIES  :(state,categories)=>state.categories = categories



};

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
};