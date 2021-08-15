import { createStore } from 'vuex'
import user from './modules/user';
import topic from './modules/topic';

const store = createStore({
  modules: {
    user,
    topic
  }
});

export default store;