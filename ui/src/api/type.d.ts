import type { AxiosRequestConfig, AxiosResponse } from "axios";
import "axios";
declare module "axios" {
    // 扩展 RouteMeta
    interface AxiosRequestConfig {
        isReturnDefaultResponse?: boolean;
    }
}
