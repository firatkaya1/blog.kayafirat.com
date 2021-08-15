
import { global } from '../config/variables';

const GLOBAL_URI = global.baseUrl

var axios = require('axios')

axios.defaults.withCredentials = true;

class service {

    getAll(domain){
        return axios.get(GLOBAL_URI+domain)
    }

    get(domain,keyword,size,pageNumber){
        return axios.get(GLOBAL_URI+domain+'/all?keyword='+keyword+'&size='+size+'&page='+pageNumber)
    }

    save(domain,body) {
        return axios.post(GLOBAL_URI+domain,body)
    }

    update(domain,body){
        return axios.put(GLOBAL_URI+domain,body)
    }

    delete(domain,id){
        return axios.delete(GLOBAL_URI+domain+""+id)
    }

    filter(domain,body){
        return axios.post(GLOBAL_URI+domain+'/filter',body);
    }

    login(body){
        return axios.post(GLOBAL_URI+'user/login',body);
    }
    logout(){
        return axios.post(GLOBAL_URI+'user/logout');
    }
    getCurrentUser(){
        return axios.get(GLOBAL_URI+'user/current');
    }
    download(domain){
        return axios.get(GLOBAL_URI+domain)
    }
}

export default new service;