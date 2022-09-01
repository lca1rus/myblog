import Vue from 'vue';
import VueRouter from 'vue-router'
Vue.use(VueRouter)

import Login from '@/pages/Login'
import Register from '@/pages/Register'
export default new VueRouter({
    routes:[
    {
        path:"/login/:keyword?",
        component:Login,
        meta:{show:true}

    },
    {
        path:"/register/:keyword?",
        component:Register,
        meta:{show:false}
    },
    {
        path:'*',
        redirect:'/login',
        meta:{show:true}
    }
    ]
})