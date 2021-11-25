import service from '../../service/service.js'

const state = {
    contacts:[]
}

const getters = {
    getAllContact:state => state.contacts
};

const actions = {
    getContacts:({commit}) => {
        service.getAll("contact/all")
        .then(response =>  {
            commit('SET_CONTACTS',response.data)
        })
    },
    deleteContact:({commit},id) => {
        service.deleteById("contact?id="+id)
        .then(() =>  {
            commit('DELETE_CONTACT',id)
            commit('alert/pushAlert',{message:"Bir iletişim bilgisi silindi.",type:'success'},{root:true})
        })
    }
};

const mutations = {
    SET_CONTACTS :(state,contacts) => state.contacts = contacts,
    DELETE_CONTACT :(state,contactId) => state.contacts = state.contacts.filter(contact => contact.contactId != contactId),

};

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
};