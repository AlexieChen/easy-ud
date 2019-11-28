package com.sucsoft.easyudcore.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * @Author: "Chenzx"
 * @Date: 2019/11/26 17:00
 * @Description:
 */
@ApiModel("文件下载信息")
public class FileDownloadDO {
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("文件分片的索引")
    private Integer sliceIndex;
    @ApiModelProperty("文件id")
    private String fileId;
    @ApiModelProperty("文件名")
    private String filePath;
    @ApiModelProperty("是否完成")
    private Boolean completed;

    public FileDownloadDO(String id, Integer sliceIndex, String filePath, Boolean completed) {
        this.id = id;
        this.sliceIndex = sliceIndex;
        this.filePath = filePath;
        this.completed = completed;
    }

    public FileDownloadDO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSliceIndex() {
        return sliceIndex;
    }

    public void setSliceIndex(Integer sliceIndex) {
        this.sliceIndex = sliceIndex;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
