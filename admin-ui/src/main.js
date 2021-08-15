import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import '../src/index.css'
import Icon from '@/components/Util/Icon.vue'
import BaseInput from '@/components/Util/BaseInput.vue' 
import BaseCheckbox from '@/components/Util/BaseCheckbox.vue' 
import BaseTextarea from '@/components/Util/BaseTextarea.vue' 
import Pagination from '@/components/Util/Pagination.vue' 

createApp(App)
    .use(router)
    .use(store)
    .component('Icon',Icon)
    .component('BaseInput',BaseInput)
    .component('BaseCheckbox',BaseCheckbox)
    .component('BaseTextarea',BaseTextarea)
    .component('Pagination',Pagination)
    .mount('#app')
