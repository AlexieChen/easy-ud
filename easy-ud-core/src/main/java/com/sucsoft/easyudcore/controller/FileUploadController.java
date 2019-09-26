package com.sucsoft.easyudcore.controller;

import com.sucsoft.easyudcore.bean.FileResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.sucsoft.easyudcore.service.FileBasicUploadService;

import java.io.IOException;
import java.util.List;

/**
 * @Author: "Chenzx"
 * @Date: 2019/9/24 15:54
 * @Description:
 */
@Api(value = "API-basic_upload", tags = "文件上传基础模块")
@RestController
@RequestMapping("/rest/basic/upload")
public class FileUploadController {

    @Autowired
    private FileBasicUploadService fileBasicUploadService;

    @RequestMapping(value = "/file", consumes = "multipart/*", headers = "content-type=multipart/form-data", method = RequestMethod.POST)
    @ApiOperation(value = "API-基础文件上传功能-1.1-单个文件上传", notes = "单个文件上传，uploadDir-上传目录")
    public FileResponse upload(@RequestParam MultipartFile file, @RequestParam String uploadDir) throws IOException {
        return fileBasicUploadService.upload(file, uploadDir);
    }

    @PostMapping(value = "/files", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    @ApiOperation(value = "API-基础文件上传功能-1.2-多个文件上传", notes = "多个文件上传，uploadDir-上传目录")
    public List<FileResponse> uploadFile(@RequestPart List<MultipartFile> files, @RequestParam String uploadDir) throws IOException {
        return fileBasicUploadService.uploadFiles(files, uploadDir);
    }
}