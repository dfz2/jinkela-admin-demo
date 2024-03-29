import service from "@/api/service";


// 管理员列表
export function adminLists(params: any) {
    return service({ url: '/api/jinkela/users', method: 'GET', params })
}

// 管理员添加
export function adminAdd(params: any) {
    return service({ url: '/api/jinkela/users', method: 'POST', data: params })
}

// 管理员编辑
export function adminDetail(params: any) {
    return service({ url: `/api/jinkela/users/${params.id}`, method: 'GET' })
}

// 管理员编辑
export function adminEdit(params: any) {
    return service({ url: `/api/jinkela/users/${params.id}`, method: 'PUT', data: params })
}

// 管理员删除
export function adminDelete(params: any) {
    return service({ url: '/api/system/admin/del', method: 'POST', params })
}

// 管理员删除
export function adminStatus(params: any) {
    return service({ url: '/api/system/admin/disable', method: 'POST', params })
}
