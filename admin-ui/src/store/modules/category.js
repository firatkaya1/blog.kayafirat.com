import service from '../../service/service.js'

const state = {
    categories:[]

}

const getters = {
    getAllCategories:state => state.categories

};

const actions = {
    getCategories:({commit}) => {
        service.getAll("category")
        .then(response =>  {
            commit('SET_CATEGORIES',response.data)
        })
    },
    saveCategory:({commit},body) => {
        service.save("category",body)
        .then(response =>  {
            commit('ADD_CATEGORY',response.data)
        })
    }

};

const mutations = {
    SET_CATEGORIES  :(state,categories)=>state.categories = categories,
    ADD_CATEGORY:(state,category) => state.categories.push({categoryId:category.id,categoryName:category.name,categoryColor:category.color,categoryTextColor:category.textColor})
};

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
};