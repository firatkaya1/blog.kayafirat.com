import service from '../../service/service.js'

const state = {
    topics:[],
    topicsDetail:[],
    topic:{},
    categories:[]

}

const getters = {
    getAllTopic:state => state.topics,
    getTopicsAllDetail:state => state.topicsDetail,
    getTopic:state => state.topic,
    getAllCategories:state => state.categories

};

const actions = {
    getTopics:({commit}) => {
        service.getAll("admin/topic/list")
        .then(response =>  {
            commit('SET_TOPICS',response.data)
        })
        .catch(error => {
            commit('alert/pushAlert',{message:"Bir hata ile karşılaşıldı:"+error,type:'error'},{root:true})
        })
    },
    getTopicsDetail:({commit}) => {
        service.getAll("admin/topic/list/detail")
        .then(response =>  {
            commit('SET_TOPICS_DETAİL',response.data)
        })
        .catch(error => {
            commit('alert/pushAlert',{message:"Bir hata ile karşılaşıldı:"+error,type:'error'},{root:true})
        })
    },
    getTopicById:({commit},id) => {
        service.getAll("admin/topic/"+id)
        .then(response =>  {
            commit('SET_TOPIC',response.data)
        })
        .catch(error => {
            commit('alert/pushAlert',{message:"Bir hata ile karşılaşıldı:"+error,type:'error'},{root:true})
        })
    },
    getCategories:({commit}) => {
        service.getAll("category")
        .then(response =>  {
            commit('SET_CATEGORIES',response.data)
        })
        .catch(error => {
            commit('alert/pushAlert',{message:"Bir hata ile karşılaşıldı:"+error,type:'error'},{root:true})
        })
    },
    saveTopic:({commit},body) => {
        service.save("post",body)
        .then(() =>  {
            commit('alert/pushAlert',{message:"Konu başarıyla kayıt edildi.",type:'success'},{root:true})
            commit('SET_TOPICS',{})
        })
        .catch(error => {
            commit('alert/pushAlert',{message:"Bir hata ile karşılaşıldı:"+error,type:'error'},{root:true})
        })
    },
    updateTopic:({commit},body) => {
        service.update("post",body)
        .then(() =>  {
            commit('alert/pushAlert',{message:"Konu başarıyla güncellendi.",type:'success'},{root:true})
            commit('SET_TOPICS',{})
        })
        .catch(error => {
            commit('alert/pushAlert',{message:"Bir hata ile karşılaşıldı:"+error,type:'error'},{root:true})
        })
    }

};

const mutations = {
    SET_TOPICS      :(state,topics) => state.topics = topics,
    SET_TOPICS_DETAİL:(state,topicsDetail) => state.topicsDetail = topicsDetail,
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