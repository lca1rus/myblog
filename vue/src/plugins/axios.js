import axios from "axios";
const request
    = axios.create({
    baseURL: 'http://localhost:8090/',
    timeout: 10000,
})

//拦截后端的接口请求
// 请求拦截器
request.interceptors.request.use(
//在session中拿到token
    //如果存在，拿到后放入请求头中
    config => {
        //NProgress.start()

        const token = window.localStorage.getItem('token')
        const username = window.localStorage.getItem('username')
            if (token) {
                config.headers.Authorization = token
            }
        if (username) {
            config.headers.username = username
        }
            return config

    }
)

// 响应拦截器
request.interceptors.response.use(

    config => {
      //  NProgress.done()

        return config.data
    }
)

export default request