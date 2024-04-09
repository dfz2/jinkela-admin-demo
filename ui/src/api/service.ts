import axios, { type AxiosRequestConfig }  from "axios";
import { ElMessage } from "element-plus";


const defaultConfig: AxiosRequestConfig = {
  withCredentials: true,
  isReturnDefaultResponse: false
}


const service = axios.create(defaultConfig);

service.interceptors.response.use((res) => {
  const isReturnDefaultResponse = res.config.isReturnDefaultResponse
  if(isReturnDefaultResponse) {
    return res
  }

  const { status, data } = res;
  if (status !== 200) {
    ElMessage({
      message: '请求错误',
      type: 'error',
    })
  }
  return data;
},
  (error) => {
    // console.log(error)
    let { response, message } = error;

    // console.log(error)
    const { status, data } = response
    // ElMessage({
    //     message: message,
    //     type: 'error',
    // });

    if (status === 401) {
      window.location.href = `/login?continue=${window.location.pathname}`
      return Promise.reject(error);
    }

    if (status === 400 || status === 409) {
      const { errorMessage } = data
      ElMessage({
        message: errorMessage,
        type: 'error',
      })
    }


    return Promise.reject(error);
  })



export default service;