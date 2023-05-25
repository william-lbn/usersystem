package com.pml.cloud.controller;

import com.pml.cloud.dao.po.AppInfo;
import com.pml.cloud.response.ResponseCode;
import com.pml.cloud.response.ResponseData;
import com.pml.cloud.service.AppInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author： libin
 * @email： 909445583@qq.com
 * @date： 2023/3/27
 * @description：查询团队下有多少在使用的应用
 * @modifiedBy：
 * @version: 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/hypeross2/api/hyperom/database")
@Tag(name="查询应用", description = "查询团队下有多少在使用的应用")
@Slf4j
public class AppNameController {


    private final AppInfoService appInfoService;

    public AppNameController(AppInfoService appInfoService) {
        this.appInfoService = appInfoService;
    }

    @Operation(summary = "根据teamId查询团队下有多少个正在使用的应用", description = "根据teamId查询团队下有多少个正在使用的应用")
    @Parameter(name = "teamId", description = "团队Id", example = "57", required = true)
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "100", description = "查询团队下有多少应用响应", content = @Content(
                            examples = {
                                    @ExampleObject(name = "返回团队下有多少应用",
                                    description = "返回团队下有多少应用",
                                    value = "{\n" +
                                            "    \"status\": 100,\n" +
                                            "    \"message\": \"Succeed.\",\n" +
                                            "    \"data\": [\n" +
                                            "        {\n" +
                                            "            \"appName\": \"文银测试应用(勿删)\",\n" +
                                            "            \"appEnName\": \"hyperos\",\n" +
                                            "            \"appId\": 2,\n" +
                                            "            \"appType\": \"java\",\n" +
                                            "            \"appIcon\": null\n" +
                                            "        },\n" +
                                            "        {\n" +
                                            "            \"appName\": \"A应用\",\n" +
                                            "            \"appEnName\": \"fff\",\n" +
                                            "            \"appId\": 3,\n" +
                                            "            \"appType\": \"java\",\n" +
                                            "            \"appIcon\": null\n" +
                                            "        },\n" +
                                            "        {\n" +
                                            "            \"appName\": \"cj-testapp\",\n" +
                                            "            \"appEnName\": \"ct\",\n" +
                                            "            \"appId\": 42,\n" +
                                            "            \"appType\": null,\n" +
                                            "            \"appIcon\": null\n" +
                                            "        }\n" +
                                            "    ],\n" +
                                            "    \"timestamp\": 1684288858038\n" +
                                            "}")
                            }
                    ))
            }
    )
    @GetMapping(value = "/app/{teamId}")
    public ResponseData<List<AppInfo>> getAppInfoList(@PathVariable Long teamId) {
        List<AppInfo> appInfoList = appInfoService.getAppInfoAll(teamId);

        return ResponseData.success(appInfoList);

    }


}
