import service from "@/api/service.ts";


// 岗位列表
export function postLists(params) {
    return service({ url: '/system/post/list', method: 'GET', params })
}
// 岗位列表
export function postAll(params) {
    return service({ url: '/system/post/all', method: 'GET',params })
}

// 添加岗位
export function postAdd(params) {
    return request.post({ url: '/system/post/add', method: 'POST', params })
}

// 编辑岗位
export function postEdit(params) {
    return request.post({ url: '/system/post/edit', method: 'POST', params })
}

// 删除岗位
export function postDelete(params) {
    return request.post({ url: '/system/post/del', method: 'POST', params })
}

// 岗位详情
export function postDetail(params) {
    return service({ url: '/system/post/detail', method: 'POST',  params })
}
