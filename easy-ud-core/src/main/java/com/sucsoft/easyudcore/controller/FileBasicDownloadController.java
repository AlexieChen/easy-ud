package com.sucsoft.easyudcore.controller;

import com.sucsoft.easyudcore.service.FIleBasicDownloadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: "Chenzx"
 * @Date: 2019/9/25 17:45
 * @Description:
 */
@Api(value = "API-文件基础下载", tags = "文件下载基础模块")
@RestController
@RequestMapping("/rest/basic/download")
public class FileBasicDownloadController {
    @Autowired
    private FIleBasicDownloadService fileBasicDownloadService;

    @GetMapping("/file")
    @ApiOperation(value = "API-文件基础下载-1.1-单个文件下载", notes = "单个文件下载，uploadDir-上传目录")
    public void basicDownload(String fileUri, HttpServletResponse response) throws IOException {
        fileBasicDownloadService.basicDownload(fileUri, response);
    }
}
