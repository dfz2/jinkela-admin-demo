-- `_jinkela_menus` definition

CREATE TABLE `_jinkela_menus` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `active` varchar(255) DEFAULT NULL,
  `arguments` varchar(255) DEFAULT NULL,
  `component` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `hidden` bit(1) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `keep_alive` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` bigint DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `sort` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- test.`_jinkela_role_menu` definition

CREATE TABLE `_jinkela_role_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `jinkela_menu_id` bigint DEFAULT NULL,
  `jinkela_role_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- test.`_jinkela_roles` definition

CREATE TABLE `_jinkela_roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `enabled` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `version` int DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- `_jinkela_user_role` definition

CREATE TABLE `_jinkela_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `jinkela_role_id` bigint DEFAULT NULL,
  `jinkela_user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



-- `_jinkela_users` definition

CREATE TABLE `_jinkela_users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `account_non_expired` bit(1) NOT NULL,
  `account_non_locked` bit(1) NOT NULL,
  `credentials_non_expired` bit(1) NOT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `avatar` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `_jinkela_users_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1000000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;









