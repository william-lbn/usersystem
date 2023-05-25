package com.pml.cloud.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pml.cloud.exception.UserSystemException;
import com.pml.cloud.response.ResponseCode;
import com.pml.cloud.response.ResponseData;
import com.pml.cloud.service.DataBaseService;
import com.pml.cloud.vo.ReqApplicationAuthorization;
import com.pml.cloud.vo.ReqDataBase;
import com.pml.cloud.vo.RspApplicationAuthorization;
import com.pml.cloud.vo.RspDataBase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author： libin
 * @email： 909445583@qq.com
 * @date： 2023/3/24
 * @description：数据库开户信息存储逻辑控制平面
 * @modifiedBy：
 * @version: 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/hypeross2/api/hyperom/database")
@Slf4j
@Tag(name="数据库开户信息存储入口", description = "数据库开户信息存储")
public class DatabaseController {

    private final DataBaseService dataBaseServiceImpl;

    public DatabaseController(DataBaseService dataBaseServiceImpl) {
        this.dataBaseServiceImpl = dataBaseServiceImpl;
    }


    /**
     * 数据库信息入库
     * @param reqDataBase 数据库请求服务
     * @return 响应
     */
    @Operation(summary = "创建数据库开户信息", description = "创建数据库开户信息逻辑")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "100", description = "创建数据库开户信息成功响应", content = @Content(
                            examples = {
                                    @ExampleObject(name = "创建数据库开户成功响应",
                                            description = "返回创建数据库开户信息成功响应",
                                            value = "{\n" +
                                                    "    \"status\": 100,\n" +
                                                    "    \"message\": \"Succeed.\",\n" +
                                                    "    \"data\": null,\n" +
                                                    "    \"timestamp\": 1684202133088\n" +
                                                    "}")
                            }
                    ))
            }
    )
    @PostMapping
    public ResponseData<String> create(@RequestBody ReqDataBase reqDataBase){

        try {
            dataBaseServiceImpl.insert(reqDataBase);
        } catch (UserSystemException e){
            log.error("create user dateBase error.", e);
            return ResponseData.fail(ResponseCode.RC101.getCode(), ResponseCode.RC101.getMessage());
        }
        return ResponseData.success(ResponseCode.RC100.getMessage());
    }

    /**
     * 数据库信息入库
     * @param reqDataBase 数据库请求服务
     * @return 响应
     */

    @Operation(summary = "更新数据库开户信息", description = "更新数据库开户信息逻辑，当前只能更新数据库开户密码")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "100", description = "更新数据库开户信息成功响应", content = @Content(
                            examples = {
                                    @ExampleObject(name = "更新数据库开户信息成功响应",
                                            description = "返回更新数据库开户信息成功响应",
                                            value = "{\n" +
                                                    "    \"status\": 100,\n" +
                                                    "    \"message\": \"Succeed.\",\n" +
                                                    "    \"data\": null,\n" +
                                                    "    \"timestamp\": 1684291824388\n" +
                                                    "}")
                            }
                    ))
            }
    )
    @PutMapping
    public ResponseData<String> update(@RequestBody ReqDataBase reqDataBase){

        try {
            dataBaseServiceImpl.update(reqDataBase);
        } catch (UserSystemException e){
            log.error("create user dateBase error.", e);
            return ResponseData.fail(ResponseCode.RC102.getCode(), ResponseCode.RC102.getMessage());
        }
        return ResponseData.success(ResponseCode.RC100.getMessage());
    }


    @Operation(summary = "根据teamId分页查询数据库开户信息", description = "根据teamId分页查询数据库开户信息")
    @Parameter(name = "teamId", description = "团队Id", example = "57", required = true)
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "100", description = "根据teamId分页查询数据库开户信息成功响应", content = @Content(
                            examples = {
                                    @ExampleObject(name = "根据teamId分页查询数据库开户信息成功响应",
                                            description = "返回根据teamId分页查询数据库开户信息成功响应",
                                            value = "{\n" +
                                                    "    \"status\": 100,\n" +
                                                    "    \"message\": \"Succeed.\",\n" +
                                                    "    \"data\": {\n" +
                                                    "        \"records\": [\n" +
                                                    "            {\n" +
                                                    "                \"id\": 5973422798536704,\n" +
                                                    "                \"teamId\": 123,\n" +
                                                    "                \"userName\": \"libin\",\n" +
                                                    "                \"sid\": \"1234567890123456789\",\n" +
                                                    "                \"serviceType\": \"0\",\n" +
                                                    "                \"env\": \"PRO\",\n" +
                                                    "                \"dataBaseUser\": \"hello\",\n" +
                                                    "                \"dataBaseSecret\": \"cG1scXdlQVNEIUAj\",\n" +
                                                    "                \"createTime\": \"2023-05-17 03:36:15\",\n" +
                                                    "                \"updateTime\": \"2023-05-17 03:36:15\"\n" +
                                                    "            },\n" +
                                                    "            {\n" +
                                                    "                \"id\": 5585693145300992,\n" +
                                                    "                \"teamId\": 123,\n" +
                                                    "                \"userName\": \"libin\",\n" +
                                                    "                \"sid\": \"1234567890123456789\",\n" +
                                                    "                \"serviceType\": \"0\",\n" +
                                                    "                \"env\": \"PRO\",\n" +
                                                    "                \"dataBaseUser\": \"test\",\n" +
                                                    "                \"dataBaseSecret\": \"cG1sMTIzNDU2\",\n" +
                                                    "                \"createTime\": \"2023-05-17 02:50:24\",\n" +
                                                    "                \"updateTime\": \"2023-05-17 02:50:24\"\n" +
                                                    "            }\n" +
                                                    "        ],\n" +
                                                    "        \"total\": 2,\n" +
                                                    "        \"size\": 10,\n" +
                                                    "        \"current\": 1,\n" +
                                                    "        \"orders\": [],\n" +
                                                    "        \"optimizeCountSql\": true,\n" +
                                                    "        \"searchCount\": true,\n" +
                                                    "        \"maxLimit\": null,\n" +
                                                    "        \"countId\": null,\n" +
                                                    "        \"pages\": 1\n" +
                                                    "    },\n" +
                                                    "    \"timestamp\": 1684305689984\n" +
                                                    "}")
                            }
                    ))
            }
    )
    @GetMapping("/{teamId}")
    public ResponseData<?> pageUserInfo(@NotBlank(message = "teamId is not blank.") @PathVariable long teamId, @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                        @RequestParam(required = false, defaultValue = "10") Integer pageSize){

        try {
            Page<RspDataBase> rspDataBasePage = dataBaseServiceImpl.getUserDataBaseListByTeamId(teamId, pageNum, pageSize);
            return ResponseData.success(rspDataBasePage);
        } catch (UserSystemException e){
            log.error("create user dateBase error.", e);
            return ResponseData.fail(ResponseCode.RC103.getCode(), ResponseCode.RC103.getMessage());
        }
    }

    @Operation(summary = "根据teamId和developer分页查询数据库开户信息", description = "根据teamId和developer分页查询数据库开户信息")
    @Parameters({
            @Parameter(name = "teamId", description = "团队Id", example = "57", required = true),
            @Parameter(name = "developer", description = "开发者Id",example = "libin", required = true)
    })
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "100", description = "根据teamId分页查询数据库开户信息成功响应", content = @Content(
                            examples = {
                                    @ExampleObject(name = "根据teamId分页查询数据库开户信息成功响应",
                                            description = "返回根据teamId分页查询数据库开户信息成功响应",
                                            value = "{\n" +
                                                    "    \"status\": 100,\n" +
                                                    "    \"message\": \"Succeed.\",\n" +
                                                    "    \"data\": {\n" +
                                                    "        \"records\": [\n" +
                                                    "            {\n" +
                                                    "                \"id\": 5974452449837056,\n" +
                                                    "                \"teamId\": 124,\n" +
                                                    "                \"userName\": \"miujianeng\",\n" +
                                                    "                \"sid\": \"9876543210\",\n" +
                                                    "                \"serviceType\": \"0\",\n" +
                                                    "                \"env\": \"PRO\",\n" +
                                                    "                \"dataBaseUser\": \"pml-test\",\n" +
                                                    "                \"dataBaseSecret\": \"cG1sbG1wcnQxQDM=\",\n" +
                                                    "                \"createTime\": \"2023-05-17 03:40:20\",\n" +
                                                    "                \"updateTime\": \"2023-05-17 03:40:20\"\n" +
                                                    "            },\n" +
                                                    "            {\n" +
                                                    "                \"id\": 5972855023992832,\n" +
                                                    "                \"teamId\": 124,\n" +
                                                    "                \"userName\": \"miujianeng\",\n" +
                                                    "                \"sid\": \"9876543210\",\n" +
                                                    "                \"serviceType\": \"0\",\n" +
                                                    "                \"env\": \"PRO\",\n" +
                                                    "                \"dataBaseUser\": \"hello\",\n" +
                                                    "                \"dataBaseSecret\": \"cG1sIVFBWkAz\",\n" +
                                                    "                \"createTime\": \"2023-05-17 03:34:00\",\n" +
                                                    "                \"updateTime\": \"2023-05-17 03:34:00\"\n" +
                                                    "            }\n" +
                                                    "        ],\n" +
                                                    "        \"total\": 2,\n" +
                                                    "        \"size\": 10,\n" +
                                                    "        \"current\": 1,\n" +
                                                    "        \"orders\": [],\n" +
                                                    "        \"optimizeCountSql\": true,\n" +
                                                    "        \"searchCount\": true,\n" +
                                                    "        \"maxLimit\": null,\n" +
                                                    "        \"countId\": null,\n" +
                                                    "        \"pages\": 1\n" +
                                                    "    },\n" +
                                                    "    \"timestamp\": 1684305634941\n" +
                                                    "}")
                            }
                    ))
            }
    )
    @GetMapping("/{teamId}/{developer}")
    public ResponseData<?> pageUserInfo(@NotBlank(message = "teamId is not blank.") @PathVariable long teamId, @NotBlank(message = "developer is not blank.") @PathVariable String developer,
                                        @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                        @RequestParam(required = false, defaultValue = "10") Integer pageSize){

        try {
            Page<RspDataBase> rspDataBasePage = dataBaseServiceImpl.getUserDataBaseListByTeamIdAndDeveloper(teamId, developer, pageNum, pageSize);
            return ResponseData.success(rspDataBasePage);
        } catch (UserSystemException e){
            log.error("create user dateBase error.", e);
            return ResponseData.fail(ResponseCode.RC104.getCode(), ResponseCode.RC104.getMessage());
        }
    }



    /**
     * 根据id删除数据库信息
     * @param id id
     */
    @DeleteMapping(value = "/{id}")
    public ResponseData<String>  delete(@PathVariable Long id){
        dataBaseServiceImpl.delete(id);
        return ResponseData.success();
    }



    @Operation(summary = "应用授权", description = "应用授权")
    @Parameters({
            @Parameter(name = "id", description = "唯一标识", example = "5585693145300992", required = true)
    })
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "100", description = "应用授权", content = @Content(
                            examples = {
                                    @ExampleObject(name = "应用授权信息成功响应",
                                            description = "返回应用授权信息成功响应",
                                            value = "{\n" +
                                                    "    \"status\": 100,\n" +
                                                    "    \"message\": \"Succeed.\",\n" +
                                                    "    \"data\": null,\n" +
                                                    "    \"timestamp\": 1684202133088\n" +
                                                    "}")
                            }
                    ))
            }
    )
    @PutMapping(value = "/{id}")
    public ResponseData<String>  applicationAuthorization(@NotBlank(message = "id is not blank.") @PathVariable Long id,
                                                          @RequestBody ReqApplicationAuthorization reqApplicationAuthorization){

        try {
            dataBaseServiceImpl.updateApplicationAuthorization(id, reqApplicationAuthorization);
            return ResponseData.success();
        }catch (UserSystemException e) {
            log.error("update application authorization error.", e);
            return ResponseData.fail(ResponseCode.RC105.getCode(), ResponseCode.RC105.getMessage());
        }

    }



    @Operation(summary = "根据id查询有多少应用进行了授权", description = "对于该数据库，根据id查询有多少应用进行了授权")
    @Parameters({
            @Parameter(name = "id", description = "数据库信息唯一标识", example = "57", required = true)
    })
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "100", description = "根据id查询有多少应用进行了授权信息成功响应", content = @Content(
                            examples = {
                                    @ExampleObject(name = "对于该数据库，根据id查询有多少应用进行了授权信息成功响应",
                                            description = "对于该数据库，根据id查询有多少应用进行了授权信息成功响应",
                                            value = "{\n" +
                                                    "    \"status\": 100,\n" +
                                                    "    \"message\": \"Succeed.\",\n" +
                                                    "    \"data\": {\n" +
                                                    "        \"records\": [\n" +
                                                    "            {\n" +
                                                    "                \"appEnName\": \"Honor of  Kings\",\n" +
                                                    "                \"appName\": \"王者荣耀\"\n" +
                                                    "            }\n" +
                                                    "        ],\n" +
                                                    "        \"total\": 1,\n" +
                                                    "        \"size\": 10,\n" +
                                                    "        \"current\": 1,\n" +
                                                    "        \"orders\": [],\n" +
                                                    "        \"optimizeCountSql\": true,\n" +
                                                    "        \"searchCount\": true,\n" +
                                                    "        \"maxLimit\": null,\n" +
                                                    "        \"countId\": null,\n" +
                                                    "        \"pages\": 1\n" +
                                                    "    },\n" +
                                                    "    \"timestamp\": 1684827415358\n" +
                                                    "}")
                            }
                    ))
            }
    )
    @GetMapping("/application/authorization/{id}")
    public ResponseData<?> pageApplicationAuthorization(@NotBlank(message = "id is not blank.") @PathVariable long id,
                                        @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                        @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        try {
            Page<RspApplicationAuthorization> rspDataBasePage = dataBaseServiceImpl.getApplicationAuthorizationListById(id, pageNum, pageSize);
            return ResponseData.success(rspDataBasePage);
        } catch (UserSystemException e){
            log.error("query application authorization error.", e);
            return ResponseData.fail(ResponseCode.RC106.getCode(), ResponseCode.RC106.getMessage());
        }
    }

}





