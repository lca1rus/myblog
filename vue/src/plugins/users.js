import axios from "@/plugins/axios";

export function GetUsersInfo() {
    return axios({
        url: 'Admin/GetUsersInfo',
        method: 'GET',
        data: {
        }
    })
}
export function LockUserById(id) {
    return axios({
        url: 'Admin/LockUser',
        method: 'PUT',
        data: {
            id
        }
    })
}
export function DeleteById(id) {
    return axios({
        url: 'Admin/DeleteById',
        method: 'DELETE',
        params: {
            id
        }
    })
}
export function FindUserByName(username) {
    return axios({
        url: 'Admin/FindUserByName',
        method: 'GET',
        params: {
            username
        }

    })
}