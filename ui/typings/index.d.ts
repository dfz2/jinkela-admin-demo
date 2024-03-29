type PromiseFun = (...arg: any[]) => Promise<any>


declare namespace API {

  interface Menu {
    path: string;
    component: string;
    hidden: string;
    keepAlive: string;
    permission: string;
    name: string;
    arguments: string;
    icon: string;
    type: string;
    active: string;
    children: Array<API.Menu>
  }


  interface UserInfo {
    username: string;
    password: string;
    enabled: boolean;
  }

}

