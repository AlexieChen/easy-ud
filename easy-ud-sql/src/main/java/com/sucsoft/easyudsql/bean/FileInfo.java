package com.sucsoft.easyudsql.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.lang.Nullable;

import java.util.Date;

/**
 * @Author: "YangJJ"
 * @Date: 2019/9/18 13:23
 * @Description:
 */
@ApiModel("文件上传表单")
public class FileInfo {
    @ApiModelProperty("文件md5")
    private String md5;
    @ApiModelProperty("时间")
    @Nullable
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;
    @ApiModelProperty("文件名")
    private String fileName;
    @ApiModelProperty("文件总大小")
    private Integer fileSize;

    @Override
    public String toString() {
        return "FileInfo{" +
                "md5='" + md5 + '\'' +
                ", date=" + date +
                ", fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                ", filePath='" + filePath + '\'' +
                ", fileStatus='" + fileStatus + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @ApiModelProperty("文件存放路径")
    private String filePath;


    public String getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(String fileStatus) {
        this.fileStatus = fileStatus;
    }

    @ApiModelProperty("文件状态，0未完成，1已完成")
    private String fileStatus;
    @ApiModelProperty("id")
    private String id;

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

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
    }
}
