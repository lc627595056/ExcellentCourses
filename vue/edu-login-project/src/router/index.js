import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/components/Login'
import Signin from '@/components/Signin'
import index from '@/components/index'

Vue.use(Router)

export default new Router({
  mode:'history',
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login,
    },{
      path:'/Login',
      name: 'Login',
      component: Login
    },{
      path:'/Signin',
      name: 'Signin',
      component: Signin
    },{
      path:'/index',
      name: 'index',
      component: index,
    }
  ]
})
