import service from "@/api/service";
import { ref } from "vue";



export function useDownloadFn(params: Record<string, any>) {

  const downloading = ref<boolean>(false)

  const createHiddenLink = (downloadUrl: string, filename: string): void => {
    const link = document.createElement("a")
    link.href = downloadUrl
    link.download = filename
    link.click()
    URL.revokeObjectURL(downloadUrl)
  }

  const downloadFn = async () => {

    try {
      downloading.value = true

      const response = await service({
        url: `/api/jinkela/s/common/download`,
        responseType: "blob",
        method: 'GET',
        params: params,
        isReturnDefaultResponse: true
      })

      createHiddenLink(
        URL.createObjectURL(response.data),
        response.headers["content-disposition"].replace("attachment;filename=", "")
      )

      downloading.value = false
    } catch (e) {
      console.log(e)
      alert("文件下载失败")
      downloading.value = false
    }

  }

  return {
    downloading,
    downloadFn
  }


}