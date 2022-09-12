import axios from "axios";
import NProgress from 'nprogress'
//import 'nprogress/nprogress.css'
const request
    = axios.create({
    baseURL: 'api/user/',
    timeout: 10000,
})


// ��������
request.interceptors.request.use(

    config => {
        //NProgress.start()
        const token = window.sessionStorage.getItem('token')

        if (token) {
            console.log(token)
            config.headers.Authorization = token

        }
        return config
    }
)

// ��Ӧ����
request.interceptors.response.use(

    config => {
      //  NProgress.done()

        return config.data
    }
)

export default request