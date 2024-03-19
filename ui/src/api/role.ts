import service from "@/api/service.ts";

// 角色列表
export function roleLists(params) {
    return service({ url: '/api/jinkela/roles', method: 'GET', params })
}

// 角色列表
export function roleAll(params) {
    return service({ url: '/api/system/role/all', method: 'GET', params })
}

// 角色列表
export function roleDetail(params) {
    return service({ url: `/api/jinkela/roles/${params.id}`, method: 'GET' })
}

// 添加角色
export function roleAdd(params) {
    return service({ url: '/api/jinkela/roles', method: 'POST', data: params })
}
// 编辑角色
export function roleEdit(params) {
    return service({ url: `/api/jinkela/roles/${params.id}`, method: 'PUT', data: params })
}
// 删除角色
export function roleDelete(params) {
    return service({ url: `/api/jinkela/roles/${params.id}`, method: 'DELETE', data: params })
}
