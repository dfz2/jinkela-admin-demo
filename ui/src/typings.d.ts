
type PromiseFun = (...arg: any[]) => Promise<any>;

declare namespace API {
	type JinkelaMenu = {
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
		children: API.Menu[];
	};

	type JinkelaUser = {
		id: string;
		username: string;
		nickname: string;
		password: string;
		enabled: boolean;
		avatar: string;
		accountNonExpired: boolean;
		accountNonLocked: boolean;
		credentialsNonExpired: boolean;
		authorities: any[];
	};


	type JinkelaRole = {
		id: string
		name: string
		remark: string
		enabled: boolean
	}
}
