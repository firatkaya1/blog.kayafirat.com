import service from '../../service/service.js'

const state = {
    reports:[]
}

const getters = {
    getAllReport:state => state.reports
};

const actions = {
    getReports:({commit}) => {
        service.getAll("report/all")
        .then(response =>  {
            commit('SET_REPORTS',response.data)
        })
    },
    deleteReport:({commit},id) => {
        service.deleteById("report?id="+id)
        .then(() =>  {
            commit('DELETE_REPORT',id)
            commit('alert/pushAlert',{message:"Bir rapor silindi.",type:'success'},{root:true})
        })
    }
};

const mutations = {
    SET_REPORTS :(state,reports) => state.reports = reports,
    DELETE_REPORT :(state,reportId) => state.reports = state.reports.filter(report => report.reportId != reportId),

};

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
};