package com.sucsoft.easyudcore.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.lang.Nullable;

import java.util.Date;

/**
 * @author : "Chenzx"
 * @date : 2019/9/18 13:23
 */
@ApiModel("文件上传表单")
public class FileForm {
    @ApiModelProperty("文件md5")
    private String md5;
    @ApiModelProperty("时间")
    @Nullable
    private Date date;
    @ApiModelProperty("文件名")
    private String fileName;
    @ApiModelProperty("分块大小")
    private Integer chunkSize;
    @ApiModelProperty("文件总大小")
    private Integer fileSize;
    @ApiModelProperty("总共的分块数目,文件总共分为多少份")
    private Integer chunkCount;
    @ApiModelProperty("分片文件索引，从1开始")
    private Integer chunkIndex;
    @ApiModelProperty("表单类型")
    private Integer type;

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    @Nullable
    public Date getDate() {
        return date;
    }

    public void setDate(@Nullable Date date) {
        this.date = date;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(Integer chunkSize) {
        this.chunkSize = chunkSize;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getChunkCount() {
        return chunkCount;
    }

    public void setChunkCount(Integer chunkCount) {
        this.chunkCount = chunkCount;
    }

    public Integer getChunkIndex() {
        return chunkIndex;
    }

    public void setChunkIndex(Integer chunkNo) {
        this.chunkIndex = chunkNo;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) { this.type = type; }
}
