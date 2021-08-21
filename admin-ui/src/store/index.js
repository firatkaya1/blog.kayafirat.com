import { createStore } from 'vuex'
import user from './modules/user';
import topic from './modules/topic';
import category from './modules/category';

const store = createStore({
  modules: {
    user,
    topic,
    category
  }
});

export default store;