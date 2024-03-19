import service from "@/api/service.ts";


// 管理员列表
export function adminLists(params) {
    return service({ url: '/api/jinkela/users', method: 'GET', params })
}

// 管理员添加
export function adminAdd(params) {
    return service({ url: '/api/jinkela/users', method: 'POST', data: params })
}

// 管理员编辑
export function adminDetail(params) {
    return service({ url: '/api/system/admin/detail', method: 'POST', params })
}

// 管理员编辑
export function adminEdit(params) {
    return service({ url: '/api/system/admin/edit', method: 'POST', params })
}

// 管理员删除
export function adminDelete(params) {
    return service({ url: '/api/system/admin/del', method: 'POST', params })
}

// 管理员删除
export function adminStatus(params) {
    return service({ url: '/api/system/admin/disable', method: 'POST', params })
}
