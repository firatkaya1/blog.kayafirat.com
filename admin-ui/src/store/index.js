import { createStore } from 'vuex'
import user from './modules/user';
import topic from './modules/topic';
import category from './modules/category';
import notification from './modules/notification';
import comment from './modules/comment';
import alert from './modules/alert';
import log from './modules/log';
import report from './modules/report';

const store = createStore({
  modules: {
    user,
    topic,
    category,
    notification,
    comment,
    alert,
    log,
    report
  }
});

export default store;