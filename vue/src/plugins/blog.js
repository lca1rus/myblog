import axios from "@/plugins/axios";

export function GetBlogs() {
    return axios({
        url: 'GetBlogs',
        method: 'GET',

    })
}

export function deleteBlogs(id) {
    return axios({
        url: 'deleteBlogs',
        method: 'DELETE',
        params: {
            id
        }
    })
}

export function updateBlog(id,title,content) {
    return axios({
        url: 'updateBlog',
        method: 'PUT',
        params: {
            id,
            title,
            content
        }
    })
}

export function insertBlog(title, content) {
    return axios({
        url: 'insertBlog',
        method: 'PUT',
        params: {
            title,
            content

        }
    })
}

