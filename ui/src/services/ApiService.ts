import axios from "axios";



export interface IResponse<T> {
    success: boolean
    message: string
    value: T
}


export class ApiService {



    public static async callGetApi<T>(url: string): Promise<IResponse<T>> {
        try {
            return await axios.get(url, {});
        } catch (e) {
            console.log(e);
            throw e
        } finally {

        }
    }



}