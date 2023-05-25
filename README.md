# 用户系统说明文档



### 项目数据定义
```
PO(Persistant Object) 持久对象 （常用）
    1、一个PO映射数据库的一行数据对象
    2、PO 仅仅用于表示数据，没有任何数据操作
BO(Business Object) 业务对象
    1、一个BO包含多个PO，是对多个PO的融合
    2、封装对象、复杂对象，里面可能包含多个类
    3、可以进行 PO 与 VO/DTO 之间的转换
DTO(Data Transfer Object) 数据传输对象 （常用）
    DTO是根据业务需求，对PO属性进行筛选的结果，隐藏真实的数据库表结构，最后转化为VO
VO(Value Object) 表现对象 （常用）
    1、VO是前端页面展示对象
    2、只包含前端需要展示的数据即可
```

```

SELECT *FROM `os_application` WHERE sys_id IN 
(SELECT resource_id FROM os_team_resource WHERE team_id='57' AND resource_type='sys'
) AND STATUS ='0';



```



分页查询各个字段的含义

| 属性名                 | 类型    | 默认值    | 描述                                                         |
| ---------------------- | ------- | --------- | ------------------------------------------------------------ |
| records                | List    | emptyList | 查询数据列表                                                 |
| total                  | Long    | 0         | 查询列表总记录数                                             |
| size                   | Long    | 10        | 每页显示条数，默认 10                                        |
| current                | Long    | 1         | 当前页                                                       |
| orders                 | List    | emptyList | 排序字段信息，允许前端传入的时候，注意 SQL 注入问题，可以使用 SqlInjectionUtils.check(...) 检查文本 |
| optimizeCountSql       | boolean | true      | 自动优化 COUNT SQL 如果遇到 jSqlParser 无法解析情况，设置该参数为 false |
| optimizeJoinOfCountSql | boolean | true      | 自动优化 COUNT SQL 是否把 join 查询部分移除                  |
| searchCount            | boolean | true      | 是否进行 count 查询，如果指向查询到列表不要查询总记录数，设置该参数为 false |
| maxLimit               | Long    |           | 单页分页条数限制                                             |
| countId                | String  |           | xml 自定义 count 查询的 statementId                          |

数据库用户名命名规则

```
https://github.com/cncounter/translation/blob/master/tiemao_2020/30_mysql_account_username/README.md
```


Swagger地址

```
http://localhost:8888/swagger-ui/index.html
```

