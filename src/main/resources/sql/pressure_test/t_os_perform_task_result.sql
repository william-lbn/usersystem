CREATE TABLE `t_os_perform_task_result` (
    `id` bigint(64) NOT NULL ,
    `perform_task_id` bigint(64) NOT NULL  COMMENT '关联t_os_pressure_task表中的id;有多个部署制品配置',
    `app_name` varchar(64) DEFAULT NULL COMMENT '应用名称',
    `env_name` varchar(200) NOT NULL COMMENT '环境名称',
    `sid` VARCHAR ( 200 ) DEFAULT NULL COMMENT '标识ID',
    `cpu` int(64) NOT NULL COMMENT 'CPU 大小，单位 C; 注意是pod中所有的CPU大小相加',
    `memory`  int(64) NOT NULL COMMENT '内存大小 单位MiB; 注意是pod中所有的内存大小相加',
    `team_id` BIGINT ( 20 ) NOT NULL COMMENT '团队Id',
    `create_user` varchar(255) NOT NULL COMMENT '创建人',
    `create_time` datetime(0) NOT NULL COMMENT '执行结果创建时间',
     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '执行结果创建任务表' ROW_FORMAT = Dynamic;