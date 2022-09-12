import Vue from 'vue';
import VueRouter from 'vue-router'
Vue.use(VueRouter)

import Home from "@/views/home/Home"

import Index from "@/views/Index.vue"
import Register from "@/views/login/register/Register"
import Login from "@/views/login/Login.vue"
import AdminManger from "@/views/Admin/AdminManger";

const routes = [

    {
        path: "/",
        component: Index,
        redirect: '/home',
        children:[
            {
				path: '/home',
				name: 'home',
				component:Home,
				meta: {title: '首页'}
			},
        ]
    },

    {
      path:"/login",
      component:Login,
      meta:{show:true},
      children:[
        {
            path:'/register',
            name:'register',
            component:Register,
			meta:{show:false}

        }
      ]
    },

    {
        path:"/AdminManger",
        component:AdminManger,
        meta:{show:false},
        // children: [
        //     {
        //         path: '/welcome',
        //         component: Welcome,
        //         meta: {
        //             title: '后台管理'
        //         }
        //     },
        //     {
        //         path: '/blogs',
        //         component: BlogList,
        //         meta: {
        //             title: '博客列表'
        //         }
        //     },
        // ]

    },



    {
        path:'*',
        redirect:'/'
    }

    
]
const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})


export default router