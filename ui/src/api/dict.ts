import service from "@/api/service";

// 字典类型列表
export function dictTypeLists(params: any) {
  return service({
    url: '/api/system/setting/dictionary-type/list',
    method: 'GET',
    params
  })
}

// 字典类型列表
export function dictTypeAll(params: any) {
  return service({
    url: '/api/system/setting/dictionary-type/all',
    method: 'GET',
    params
  })
}

// 添加字典类型
export function dictTypeAdd(params: any) {
  return service({
    url: '/api/system/setting/dictionary-type/add',
    method: 'POST',
    params
  })
}

// 编辑字典类型
export function dictTypeEdit(params: any) {
  return service({
    url: '/api/system/setting/dictionary-type/edit',
    method: 'POST',
    params
  })
}

// 删除字典类型
export function dictTypeDelete(params: any) {
  return service({
    url: '/api/system/setting/dictionary-type/del',
    method: 'POST',
    params
  })
}

// 字典数据列表
export function dictDataLists(params: any) {
  return service(
    {
      url: '/api/system/setting/dictionary-data/list',
      method: 'GET',
      params
    },
    {
      ignoreCancelToken: true
    }
  )
}

// 字典数据列表
export function dictDataAll(params) {
  return service(
    {
      url: '/api/system/setting/dictionary-data/all',
      method: 'GET',
      params
    },
    {
      ignoreCancelToken: true
    }
  )
}

// 添加字典数据
export function dictDataAdd(params) {
  return service({
    url: '/api/system/setting/dictionary-data/add',
    method: 'POST',
    params
  })
}

// 编辑字典数据
export function dictDataEdit(params) {
  return service({
    url: '/api/system/setting/dictionary-data/edit',
    method: 'POST',
    params
  })
}

// 删除字典数据
export function dictDataDelete(params) {
  return service({
    url: '/api/system/setting/dictionary-data/del',
    method: 'POST',
    params
  })
}
