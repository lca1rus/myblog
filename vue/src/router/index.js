import Vue from 'vue';
import VueRouter from 'vue-router'
Vue.use(VueRouter)

import Home from "@/views/home/Home"

import Index from "@/views/Index.vue"
import Register from "@/views/login/register/Register"
import Login from "@/views/login/Login.vue"
import AdminManger from "@/views/Admin/AdminManger";
import Blog from "@/views/blog/IndexBlog"
import BlogList from "@/views/Admin/Admincontrol/AdminBlog"
import ViewerList from "@/views/Admin/Admincontrol/AdminUser"
import WriteBlog from "@/views/blog/WriteBlog"
import Detailed from "@/views/blog/Detailed"
import ChatView from "@/views/Talk/ChatView"
const routes = [

    {
        path: "/",
        component: Index,
        redirect: '/home',
        children: [
            {
                path: '/home',
                name: 'home',
                component: Home,
                meta: { title: '首页',
                    requireAuth: false}
            },
            {
                path: '/blog',
                name: 'blog',
                component: Blog
            }
        ]
    },

    {
        path: "/login",
        component: Login,
        meta: {requireAuth: false},
        children: [
            {
                path: '/register',
                name: 'register',
                component: Register,
                meta: { requireAuth: false }

            }
        ]
    },

    {
        path: "/AdminManger",
        component: AdminManger,
        meta: {requireAuth: true  },
        children: [

            {
                path: '/BlogList',
                component: BlogList,
                meta: {
                    title: '博客列表'
                }
            },
            {
                path:'/ViewerList',
                component:ViewerList,
                meta:{
                    title:'用户列表'
                }
            }
        ]

    },
    {
        path:"/Detailed/:id",
        name:'Detailed',
        component:Detailed,
  },
    {
        path: "/WriteBlog",
        component: WriteBlog,
        meta: { show: false },
        children: [
        ]
    },
    {
        path:"/ChatView",
        component:ChatView,
    },
    {
        path: '*',
        redirect: '/'
    }


]
const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})


export default router