package com.sucsoft.easyudcore.controller;

import com.sucsoft.easyudcore.service.FileLoadService;
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
 * @Date: 2019/9/26 11:03
 * @Description:
 */
@Api(value = "API-文件预览", tags = "前端预览文件基础模块")
@RestController
@RequestMapping("/easyud/load")
public class FileLoadController {
    @Autowired
    private FileLoadService fileLoadService;

    @GetMapping("/file")
    @ApiOperation(value = "API-文件预览-1.1-单个文件预览", notes = "文件预览，id-上传文件的id")
    public void loadFile(String id, HttpServletResponse response) throws IOException {
        fileLoadService.loadFile(id, response);
    }

}
