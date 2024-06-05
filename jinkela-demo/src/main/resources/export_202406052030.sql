INSERT INTO test2.`_jinkela_menus` (id,created_at,created_by,modified_at,modified_by,active,params,component,enabled,hidden,icon,keep_alive,name,parent_id,`path`,permission,sort,title,`type`) VALUES
	 (100,NULL,NULL,NULL,NULL,'','','',1,0,'setting',0,'系统设置',0,'','',44,NULL,'M'),
	 (101,NULL,NULL,NULL,NULL,'','','AdminList',1,0,'User',1,'管理员',100,'/permission/admin','guanliyuan',0,NULL,'C'),
	 (102,NULL,NULL,NULL,NULL,'','','',1,0,'',0,'管理员详情',101,'','guanliyuanxiangqing',0,NULL,'A'),
	 (103,NULL,NULL,NULL,NULL,'','','',1,0,'',0,'管理员新增',101,'','xinzhengguanliyuan',0,NULL,'A'),
	 (104,NULL,NULL,NULL,NULL,'','','',1,0,'',0,'管理员编辑',101,'','gengxinguanliyuan',0,NULL,'A'),
	 (105,NULL,NULL,NULL,NULL,'','','',1,0,'',0,'管理员删除',101,'','shanchuguanliyuan',0,NULL,'A'),
	 (106,NULL,NULL,'2024-03-31 22:24:22.977027','1000000','','','',1,0,'',0,'管理员状态',101,'','guanliyuanzhuangtai',0,NULL,'A'),
	 (110,NULL,NULL,NULL,NULL,'','','',1,0,'Female',1,'角色管理',100,'/permission/role',NULL,0,NULL,'M'),
	 (111,NULL,NULL,'2024-03-31 22:26:14.300559','1000000','','','',1,0,'',0,'角色详情',110,'','qudejuesexinxi',0,NULL,'A'),
	 (112,NULL,NULL,'2024-03-31 22:36:43.343212','1000000','','','',1,0,'',0,'角色新增',110,'','xinzhengjuese',0,NULL,'A');
INSERT INTO test2.`_jinkela_menus` (id,created_at,created_by,modified_at,modified_by,active,params,component,enabled,hidden,icon,keep_alive,name,parent_id,`path`,permission,sort,title,`type`) VALUES
	 (113,NULL,NULL,'2024-03-31 23:03:32.953460','1000000','','','',1,0,'',0,'角色编辑',110,'','gengxinjuese',0,NULL,'A'),
	 (114,NULL,NULL,'2024-03-31 23:03:44.014922','1000000','','','',1,0,'',0,'角色删除',110,'','shanchujuese',0,NULL,'A'),
	 (120,NULL,NULL,NULL,NULL,'','','MenuList',1,0,'Operation',1,'菜单管理',100,'/permission/menu','caidanguanli',0,NULL,'C'),
	 (121,NULL,NULL,NULL,NULL,'','','',1,0,'',0,'菜单详情',120,'','system:menu:detail',0,NULL,'A'),
	 (122,NULL,NULL,NULL,NULL,'','','',1,0,'',0,'菜单新增',120,'','system:menu:add',0,NULL,'A'),
	 (123,NULL,NULL,NULL,NULL,'','','',1,0,'',0,'菜单编辑',120,'','system:menu:edit',0,NULL,'A'),
	 (124,NULL,NULL,NULL,NULL,'','','',1,0,'',0,'菜单删除',120,'','system:menu:del',0,NULL,'A'),
	 (131,NULL,NULL,NULL,NULL,'','','DepartmentList',1,0,'Coordinate',1,'部门管理',100,'department','system:dept:list',0,NULL,'C'),
	 (132,NULL,NULL,NULL,NULL,'','','',1,0,'',0,'部门详情',131,'','system:dept:detail',0,NULL,'A'),
	 (133,NULL,NULL,NULL,NULL,'','','',1,0,'',0,'部门新增',131,'','system:dept:add',0,NULL,'A');
INSERT INTO test2.`_jinkela_menus` (id,created_at,created_by,modified_at,modified_by,active,params,component,enabled,hidden,icon,keep_alive,name,parent_id,`path`,permission,sort,title,`type`) VALUES
	 (134,NULL,NULL,NULL,NULL,'','','',1,0,'',0,'部门编辑',131,'','system:dept:edit',0,NULL,'A'),
	 (135,NULL,NULL,NULL,NULL,'','','',1,0,'',0,'部门删除',131,'','system:dept:del',0,NULL,'A'),
	 (140,NULL,NULL,NULL,NULL,'','','PostList',1,0,'PriceTag',1,'岗位管理',100,'post','system:post:list',0,NULL,'C'),
	 (141,NULL,NULL,NULL,NULL,'','','',1,0,'',0,'岗位详情',140,'','system:post:detail',0,NULL,'A'),
	 (142,NULL,NULL,NULL,NULL,'','','',1,0,'',0,'岗位新增',140,'','system:post:add',0,NULL,'A'),
	 (143,NULL,NULL,NULL,NULL,'','','',1,0,'',0,'岗位编辑',140,'','system:post:edit',0,NULL,'A'),
	 (144,NULL,NULL,NULL,NULL,'','','',1,0,'',0,'岗位删除',140,'','system:post:del',0,NULL,'A'),
	 (515,NULL,NULL,NULL,NULL,'','','DictList',1,0,'Box',0,'字典管理',100,'dict','setting:dict:````````type````````:list',0,NULL,'C'),
	 (516,NULL,NULL,NULL,NULL,'','','',1,0,'',0,'字典类型新增',515,'','setting:dict:````````type````````:add',0,NULL,'A'),
	 (517,NULL,NULL,NULL,NULL,'','','',1,0,'',0,'字典类型编辑',515,'','setting:dict:````````type````````:edit',0,NULL,'A');
INSERT INTO test2.`_jinkela_menus` (id,created_at,created_by,modified_at,modified_by,active,params,component,enabled,hidden,icon,keep_alive,name,parent_id,`path`,permission,sort,title,`type`) VALUES
	 (518,NULL,NULL,NULL,NULL,'','','',1,0,'',0,'字典类型删除',515,'','setting:dict:````````type````````:del',0,NULL,'A'),
	 (519,NULL,NULL,NULL,NULL,'/dev_tools/dict','','setting/dict/data/index',1,0,'',0,'字典数据管理',600,'dict/data','setting:dict:data:list',0,NULL,'C'),
	 (521,NULL,NULL,NULL,NULL,'/permission/role','','RoleAuthorize',1,1,'Box',0,'测试页面',110,'/permission/role/:id/authorize','jueseshouquan',0,'测试页面','C'),
	 (522,NULL,NULL,NULL,NULL,'/permission/role','','RoleList',1,0,'Female',0,'测试页面2',110,'/permission/role','jueseguanli',0,'测试页面2','C'),
	 (523,NULL,NULL,NULL,NULL,'','','MenuList2',1,0,'Operation',1,'菜单管理2',100,'/permission/menu2','caidanguanli2',0,NULL,'C'),
	 (524,NULL,NULL,NULL,NULL,'','','',1,0,'Pear',0,'举个栗子',0,'/demo','',0,'举个栗子','M'),
	 (525,NULL,NULL,NULL,NULL,'','','TestDownload',1,0,'Download',0,'测试下载文件',524,'/demo/download','ceshixiazaiwenjian',0,'测试下载文件','C');
INSERT INTO test2.`_jinkela_role_menu` (id,created_at,created_by,modified_at,modified_by,jinkela_menu_id,jinkela_role_id) VALUES
	 (8,NULL,NULL,NULL,NULL,100,1),
	 (9,NULL,NULL,NULL,NULL,101,1),
	 (10,NULL,NULL,NULL,NULL,120,1),
	 (11,NULL,NULL,NULL,NULL,110,1),
	 (12,NULL,NULL,NULL,NULL,521,1),
	 (13,NULL,NULL,NULL,NULL,522,1),
	 (14,NULL,NULL,NULL,NULL,523,1),
	 (15,NULL,NULL,NULL,NULL,103,1),
	 (16,NULL,NULL,NULL,NULL,104,1),
	 (17,NULL,NULL,NULL,NULL,105,1);
INSERT INTO test2.`_jinkela_role_menu` (id,created_at,created_by,modified_at,modified_by,jinkela_menu_id,jinkela_role_id) VALUES
	 (18,NULL,NULL,NULL,NULL,524,1),
	 (19,NULL,NULL,NULL,NULL,525,1);
INSERT INTO test2.`_jinkela_roles` (id,created_at,created_by,modified_at,modified_by,enabled,name,version,remark,deleted) VALUES
	 (1,NULL,NULL,'2024-03-26 21:35:16.919849','1000000','1','admin',5,'12221',0);
INSERT INTO test2.`_jinkela_user_role` (id,created_at,created_by,modified_at,modified_by,jinkela_role_id,jinkela_user_id) VALUES
	 (1,NULL,NULL,NULL,NULL,1,1000000);
INSERT INTO test2.`_jinkela_users` (id,created_at,created_by,modified_at,modified_by,account_non_expired,account_non_locked,credentials_non_expired,enabled,password,username,nickname,avatar,deleted) VALUES
	 (1000000,'2024-03-23 14:28:42.651559',NULL,'2024-03-23 14:28:42.651559','1000000',1,1,1,1,'{bcrypt}$2a$10$7n/Oroz9u3cr.mgnBdfjROkJAY5R25T.lOZU2sq1vZGUAj3ZlM66K','superadmin','superadmin12313','data:image/png;base64,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',0),
	 (1000001,'2024-03-30 21:00:03.529503','1000000','2024-03-31 22:33:51.486867','1000000',1,1,1,1,'{bcrypt}$2a$10$6OSWxhmP/lxj7mU5fTs.nOxGpsU3dGUz31jFH1P43PTo6//5hAySq','1351351','1235153',NULL,0),
	 (1000002,'2024-03-30 21:00:14.799164','1000000','2024-03-30 21:40:33.090195','1000000',1,1,1,0,'{bcrypt}$2a$10$PiFgb9cHghgnjdCvj4Nx9ebc5CG3QoR6gjrqm72Rf6.mbrsPkpO3i','12351351531','superadmin',NULL,1),
	 (1000003,'2024-03-30 21:00:30.953761','1000000','2024-03-30 21:40:31.198312','1000000',1,1,1,0,'{bcrypt}$2a$10$80mp.8MVTf6lwooyquDQwuLCR9IHG..9.m3VH1kkWqJR5py5GLR/i','1231553','superadmin',NULL,1),
	 (1000004,'2024-03-30 21:02:57.495372','1000000','2024-03-30 21:40:29.762035','1000000',1,1,1,0,'{bcrypt}$2a$10$sy67ycZ3a6/gKFUxX.T99O6uaCxL9QEC1aFN8ZhS0XAvzl02WS5Va','supseradmin','12315315',NULL,1),
	 (1000006,'2024-03-30 21:33:50.629197','1000000','2024-03-30 21:40:27.425922','1000000',1,1,1,0,'{bcrypt}$2a$10$gQlriTR.2M1jtdSUNQ1r0.jgDlykRRowue8siBIbFmd45QHBp1wMa','1231','12351',NULL,1),
	 (1000007,'2024-03-30 21:35:29.506615','1000000','2024-03-30 21:36:55.065650','1000000',1,1,1,0,'{bcrypt}$2a$10$RTNOZL2TA8kIzkbC/poOBeREJggkYs2ivpotnT8tNuknlPBqzAMtq','1235','125315',NULL,1);
