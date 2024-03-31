type PromiseFun = (...arg: any[]) => Promise<any>


declare namespace API {

  interface JinkelaMenu {
    id: string;
    path: string;
    component: string;
    hidden: string;
    keepAlive: string;
    permission: string;
    name: string;
    params: string;
    icon: string;
    type: string;
    active: string;
    children: API.Menu[]
  }


  interface JinkelaUser {
    id: string | number;
    username: string;
    nickname: string;
    password: string;
    enabled: boolean;
    avatar: string;
    accountNonExpired: boolean;
    accountNonLocked: boolean;
    credentialsNonExpired: boolean;
    authorities: any[];
  }

}

