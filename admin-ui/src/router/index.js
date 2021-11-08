import { createRouter, createWebHistory } from 'vue-router'
import store from '../store/index.js'

import Home from '../views/Home.vue'
import Login from '../views/Login.vue'

import UserAdd from '@/components/User/Add.vue'
import UserList from '@/components/User/List.vue'


import PostAdd from '@/components/Post/Add.vue'
import PostList from '@/components/Post/List.vue'

import CommentAdd from '@/components/Comment/Add.vue'
import CommentList from '@/components/Comment/List.vue'

import ImageList from '@/components/Image/ImageList.vue'
import Mail from '@/components/Mail/Mail.vue'
import MailList from '@/components/Mail/List.vue'

import LogList from '@/components/Log/LogList.vue'

import ReportList from '@/components/Report/ReportList.vue'

import ContactList from '@/components/Contact/ContactList.vue'

import Notification from '@/components/Notification/Notification.vue'
import NotificationList from '@/components/Notification/List.vue'

import Settings from '@/components/Settings/Settings.vue'


const routes = [
  {
    path: '',
    redirect: "/login",
  },
  {
    path:'/login',
    name:"Login",
    component:Login
  },
  {
    path:'/home',
    name:"Home",
    component:Home,
    children: [
      {
        path: 'user',
        component: UserAdd
      },
      {
        path: 'user/list',
        component: UserList
      },
      {
        path: 'post',
        component: PostAdd
      },
      {
        path: 'post/list',
        component: PostList
      },
      {
        path: 'comment',
        component: CommentAdd
      },
      {
        path: 'comment/list',
        component: CommentList
      },
      {
        path: 'image',
        component: ImageList
      },
      {
        path: 'notification',
        component: Notification
      },
      {
        path: 'notification/list',
        component: NotificationList
      },
      {
        path: 'mail',
        component: Mail
      },
      {
        path: 'mail/list',
        component: MailList
      },
      {
        path: 'log',
        component: LogList
      },
      {
        path: 'report',
        component: ReportList
      },
      {
        path: 'contact',
        component: ContactList
      },
      {
        path: 'settings',
        component: Settings
      }
    ]
  },
  
  {
    path: '/:pathMatch(.*)*',
    redirect: "/login",
  },
]

const router = createRouter({
  linkActiveClass: "active",
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach((to, from, next) => {
  var isAuthenticate = (Object.keys(store.state.user.loggedUser).length != 0) ? true : false

  if ((to.name != 'Login' && to.name != 'Diagnostic') && !isAuthenticate) {
    next({ name: 'Login' })
  }
  else
    next()
})
export default router