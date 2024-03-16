import service from "@/api/service.ts";


// 部门列表
export function deptLists(params) {
    return service({ url: '/system/dept/list', method: 'GET', params })
}

// 添加部门
export function deptAdd(params) {
    return request.post({ url: '/system/dept/add',  method: 'POST', params })
}

// 编辑部门
export function deptEdit(params) {
    return request.post({ url: '/system/dept/edit', method: 'POST',params })
}

// 删除部门
export function deptDelete(params) {
    return request.post({ url: '/system/dept/del', method: 'POST',params })
}

// 部门详情
export function deptDetail(params) {
    return service({ url: '/system/dept/detail', method: 'POST', params })
}
