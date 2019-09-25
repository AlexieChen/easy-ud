package com.sucsoft.easyudsql.controller;

import com.sucsoft.easyudsql.bean.FileInfo;
import com.sucsoft.easyudsql.service.FileInfoStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文件增删改查接口
 *
 * @author YangJJ
 * @date 2019/9/25 13:50
 */
@Api(value = "API-file_storage", tags = "文件存储查询模块")
@RestController
@RequestMapping("/rest/file/storage")
public class FileInfoStorageController {
    @Autowired
    private FileInfoStorageService fileInfoStorageService;

    @RequestMapping(value = "/query/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "API-queryFileInfoById-通过id查找文件信息", notes = "查询文件")
    public FileInfo queryFileInfoById(@PathVariable String id) {
        return fileInfoStorageService.queryFileInfoById(id);
    }

    @RequestMapping(value = "/query/md5", method = RequestMethod.POST)
    @ApiOperation(value = "API-queryFileInfoByMd5-通过md5查找文件信息", notes = "查询文件")
    public FileInfo queryFileInfoByMd5(@PathVariable String md5) {
        return fileInfoStorageService.queryFileInfoByMd5(md5);
    }

    @RequestMapping(value = "/query/list", method = RequestMethod.GET)
    @ApiOperation(value = "API-queryAllFileInfo-查询所有文件", notes = "查询所有文件信息")
    public List<FileInfo> queryAllFileInfo() {
        return fileInfoStorageService.queryAllFileInfo();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "API-saveFileInfo-文件存储", notes = "文件存储")
    public void saveFileInfo(@RequestParam FileInfo fileInfo) {
        fileInfoStorageService.saveFileInfo(fileInfo);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "API-updateFileInfo-文件更新", notes = "文件更新")
    public void updateFileInfo(@RequestParam FileInfo fileInfo) {
        fileInfoStorageService.updateFileInfo(fileInfo);
    }
}
