import service from "@/api/service";


// 岗位列表
export function postLists(params: any) {
    return service({ url: '/system/post/list', method: 'GET', params })
}
// 岗位列表
export function postAll(params: any) {
    return service({ url: '/system/post/all', method: 'GET',params })
}

// 岗位详情
export function postDetail(params: any) {
    return service({ url: '/system/post/detail', method: 'POST',  params })
}
