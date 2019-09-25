package com.sucsoft.easyudsql.controller;

import com.sucsoft.easyudsql.service.FileTypeLimitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件上传类型限制接口
 *
 * @author YangJJ
 * @date 2019/9/25 13:51
 */
@Api(value = "API-type_limit", tags = "文件上传类型限制模块")
@RestController
@RequestMapping("/rest/type/limit")
public class FileTypeLimitController {
    @Autowired
    private FileTypeLimitService fileTypeLimitService;

    @RequestMapping(value = "/query/{filePath}", method = RequestMethod.GET)
    @ApiOperation(value = "API-fileAllowable-文件是否支持上传", notes = "true表示支持，false表示不支持")
    public Boolean fileAllowable(@PathVariable String filePath) {
        return fileTypeLimitService.fileAllowable(filePath);
    }
}
