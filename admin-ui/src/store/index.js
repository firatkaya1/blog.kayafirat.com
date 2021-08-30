import { createStore } from 'vuex'
import user from './modules/user';
import topic from './modules/topic';
import category from './modules/category';
import notification from './modules/notification';

const store = createStore({
  modules: {
    user,
    topic,
    category,
    notification
  }
});

export default store;