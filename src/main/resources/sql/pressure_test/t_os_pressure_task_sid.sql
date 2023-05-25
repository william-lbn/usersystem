CREATE TABLE `t_os_pressure_task` (
    `id` bigint(64) NOT NULL ,
    `sid_id` bigint(64) NOT NULL  COMMENT '关联t_os_pressure_task表中的id;有多个标识属性',
    `source_id_key` varchar(200) DEFAULT NULL COMMENT '源标识key',
    `source_id_value` varchar(200) NOT NULL COMMENT '源标识value',
    `target_id_key` varchar(200) DEFAULT NULL COMMENT '目的标识key',
    `target_id_value` varchar(200) NOT NULL COMMENT '目的标识value',
    `team_id` BIGINT ( 20 ) NOT NULL COMMENT '团队Id',
    `create_user` varchar(255) NOT NULL COMMENT '创建人',
    `create_time` datetime(0) NOT NULL COMMENT '压测任务创建时间',
     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '压测任务表' ROW_FORMAT = Dynamic;