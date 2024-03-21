import service from "@/api/service";

// 菜单列表
export function menuLists(params) {
  return service({ url: '/api/jinkela/menus', method: 'GET', params })
}

// 添加菜单
export function menuAdd(params) {
  return service({ url: '/api/system/menu/add', method: 'POST', params })
}

// 编辑菜单
export function menuEdit(params) {
  return service({ url: '/api/system/menu/edit', method: 'POST', params })
}

// 菜单删除
export function menuDelete(params) {
  return service({ url: '/api/system/menu/del', method: 'POST', params })
}

// 菜单删除
export function menuDetail(params) {
  return service({ url: '/api/system/menu/detail', method: 'POST', params })
}
