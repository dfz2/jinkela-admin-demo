import axios from "axios";
import { ElMessage } from "element-plus";

const service = axios.create({});

service.interceptors.response.use((res) => {
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
    console.log(error)
    let { response, message } = error;
    const { status } = response
    // ElMessage({
    //     message: message,
    //     type: 'error',
    // });

    if (status === 401) {
      window.location.href('/login')
      return false
    }

    return Promise.reject(error);
  })



export default service;