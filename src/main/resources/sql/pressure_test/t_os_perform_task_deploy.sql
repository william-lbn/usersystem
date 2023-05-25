CREATE TABLE `t_os_perform_task_deploy` (
    `id` bigint(64) NOT NULL ,
    `perform_task_id` bigint(64) NOT NULL  COMMENT '关联t_os_pressure_task表中的id;有多个部署制品配置',
    `image_name` varchar(200) DEFAULT NULL COMMENT '制品名称',
    `cpu` int(64) NOT NULL COMMENT 'CPU 大小，单位 C',
    `memory`  int(64) NOT NULL COMMENT '内存大小 单位MiB',
    `team_id` BIGINT ( 20 ) NOT NULL COMMENT '团队Id',
    `create_user` varchar(255) NOT NULL COMMENT '创建人',
    `create_time` datetime(0) NOT NULL COMMENT '部署制品配置创建时间',
     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部署制品配置创建任务表' ROW_FORMAT = Dynamic;