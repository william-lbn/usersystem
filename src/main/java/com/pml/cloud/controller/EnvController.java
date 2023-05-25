package com.pml.cloud.controller;

import com.pml.cloud.dao.po.AppInfo;
import com.pml.cloud.dao.po.EnvInfo;
import com.pml.cloud.response.ResponseData;
import com.pml.cloud.service.EnvInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author： libin
 * @email： 909445583@qq.com
 * @date： 2023/3/27
 * @description：
 * @modifiedBy：
 * @version: 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/hypeross2/api/hyperom/database")
@Tag(name="查询环境", description = "查询团队下有多少个环境")
@Slf4j
public class EnvController {

    private final EnvInfoService envInfoService;

    public EnvController(EnvInfoService envInfoService) {
        this.envInfoService = envInfoService;
    }

    @Operation(summary = "查询团队下有多少个环境", description = "根据teamId查询团队下有多少个环境")
    @Parameter(name = "teamId", description = "团队Id", example = "123", required = true)
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "100", description = "查询团队下有多少个环境响应", content = @Content(
                            examples = {
                                    @ExampleObject(name = "返回团队下有多少个环境",
                                            description = "返回团队下有多少个环境",
                                            value = "{\n" +
                                                    "    \"status\": 100,\n" +
                                                    "    \"message\": \"Succeed.\",\n" +
                                                    "    \"data\": [\n" +
                                                    "        {\n" +
                                                    "            \"env\": \"PRD\"\n" +
                                                    "        },\n" +
                                                    "        {\n" +
                                                    "            \"env\": \"PRE\"\n" +
                                                    "        },\n" +
                                                    "        {\n" +
                                                    "            \"env\": \"SIT\"\n" +
                                                    "        }\n" +
                                                    "    ],\n" +
                                                    "    \"timestamp\": 1684306125247\n" +
                                                    "}")
                            }
                    ))
            }
    )
    @GetMapping(value = "/env/{teamId}")
    public ResponseData<List<EnvInfo>> getEnvInfoList(@PathVariable Long teamId) {
        List<EnvInfo> envInfoList = envInfoService.getEnvInfoAll(teamId);

        return ResponseData.success(envInfoList);

    }
}
