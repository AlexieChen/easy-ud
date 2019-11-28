package com.sucsoft.easyudcore.controller;

import com.sucsoft.easyudexception.exception.MyFileNotFoundException;
import com.sucsoft.easyudcore.service.FIleBasicDownloadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @Author: "Chenzx"
 * @Date: 2019/9/25 17:45
 * @Description:
 */
@Api(value = "API-文件基础下载", tags = "文件下载基础模块")
@RestController
@RequestMapping("esayud/basic/download")
public class FileBasicDownloadController {
    @Autowired
    private FIleBasicDownloadService fileBasicDownloadService;

    @GetMapping("/file")
    @ApiOperation(value = "API-文件基础下载-1.1-单个文件下载", notes = "单个文件下载，id-上传文件id")
    public ResponseEntity basicDownload(String id) throws MyFileNotFoundException , UnsupportedEncodingException {
        return fileBasicDownloadService.downloadFile(id);
    }

    @GetMapping("/afile")
    @ApiOperation(value = "API-文件基础下载-1.1-单个文件下载", notes = "多线程的文件下载，id-被下载文件的id")
    public List<ResponseEntity> asyncDownload(String id) throws MyFileNotFoundException  {
        return fileBasicDownloadService.asyncDownloadFile(id);
    }

    @GetMapping("/resume")
    @ApiOperation(value = "API-文件基础下载-1.1-单个文件下载恢复", notes = "单个文件下载恢复，id-被下载文件的id")
    public ResponseEntity asyncDownload(@RequestParam  String id, @RequestParam  Long startPos,@RequestParam(required = false) Long endPos)  {
        return fileBasicDownloadService.resumeDownload(id,startPos,endPos);
    }
}
