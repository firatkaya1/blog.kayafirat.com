import { createStore } from 'vuex'
import user from './modules/user';
import topic from './modules/topic';
import category from './modules/category';
import notification from './modules/notification';
import comment from './modules/comment';

const store = createStore({
  modules: {
    user,
    topic,
    category,
    notification,
    comment
  }
});

export default store;