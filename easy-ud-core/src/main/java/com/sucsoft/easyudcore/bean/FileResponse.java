package com.sucsoft.easyudcore.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *  @author : "Chenzx"
 *  @date : 2019/9/19 13:39
 */
@ApiModel("文件上传返回信息")
public class FileResponse {
    @ApiModelProperty("文件名")
    private String fileName;
    @ApiModelProperty("上传路径")
    private String uploadPath;
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("md5")
    private String md5;
    @ApiModelProperty("文件上传状态")
    private Integer status;

    public FileResponse() {
    }

    public FileResponse(String fileName, String uploadPath, String md5, Integer status) {
        this.fileName = fileName;
        this.uploadPath = uploadPath;
        this.md5 = md5;
        this.status = status;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
