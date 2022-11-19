import axios from "@/plugins/axios";

export function WriteBlog(WriteBlogInfo) {
    return axios({
        url: 'WriteBlog',
        method:'PUT',
        data: {
            ...WriteBlogInfo
        }

    })
}
export function getArticle(id) {
    return axios({
        url: 'Article/getArticle',
        method: 'GET',
        params: {
            id
        }
    })
}

export function getAdminBlogList(pagenums) {
    return axios({
        url: 'Admin/getAdminBlogList',
        method: 'GET',
        params: {
            pagenums
        }

    })
}
export function getIndexBlogList(pagenums) {
    return axios({
        url: 'index/getIndexBlogList',
        method: 'GET',
        params: {
            pagenums
        }

    })
}
export function GetBlogByName(name) {
    return axios({
        url: 'Admin/GetBlogByName',
        method: 'GET',
        params: {
           name
        }

    })
}



export function deleteBlogs(id) {
    return axios({
        url: 'Admin/deleteBlogs',
        method: 'DELETE',
        params: {
            id
        }
    })
}

export function updateBlog(id,title,content) {
    return axios({
        url: 'Admin/updateBlog',
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
        url: 'Admin/insertBlog',
        method: 'PUT',
        params: {
            title,
            content

        }
    })
}

