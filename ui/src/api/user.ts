import service from "@/api/service.ts";

export const loginApi = (params: API.LoginDTO) => {
    const formData = new FormData()
    formData.append("username", params.username)
    formData.append("password", params.password)
    return service({
        url: '/api/login',
        data: formData,
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
        }
    })
}

export const logoutApi = () => {
    return service({
        url: '/api/logout',
        method: 'POST',
    })
}


export const getUserinfoApi = () => {
    return service({
        url: '/api/jinkela/s/currentUser',
        method: 'GET',
    })
}


export const getPublicKeyApi = () => {
    return service({
        url: '/api/jinkela/open/publickey',
        method: 'GET',
    })
}


export const getMenusApi = () => {
    return service({
        url: '/api/jinkela/s/menus',
        method: 'GET',
    })
}

