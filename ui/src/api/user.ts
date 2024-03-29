import service from "@/api/service";

export const loginApi = (params: any) => {
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
        url: '/api/jinkela/s/userinfo',
        method: 'GET',
    })
}


export const getPublicKeyApi = () => {
    return service({
        url: '/api/jinkela/open/publickey',
        method: 'GET',
    })
}


export const getMenusApi = (jinkelaUserId: string) => {
    return service({
        url: `/api/jinkela/s/${jinkelaUserId}/menus`,
        method: 'GET',
    })
}

