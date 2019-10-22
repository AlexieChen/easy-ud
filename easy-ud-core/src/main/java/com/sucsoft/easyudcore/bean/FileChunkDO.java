package com.sucsoft.easyudcore.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author : "Chenzx"
 * @date : 2019/9/20 16:19
 */
@ApiModel("文件分片数据表")
public class FileChunkDO {
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("文件分片的MD5")
    private String sliceMd5;
    @ApiModelProperty("文件的md5")
    private String md5;
    @ApiModelProperty("文件分片的索引")
    private Integer sliceIndex;
    @ApiModelProperty("文件id")
    private String fileId;
    @ApiModelProperty("是否完成")
    private Boolean completed;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSliceMd5() {
        return sliceMd5;
    }

    public void setSliceMd5(String sliceMd5) {
        this.sliceMd5 = sliceMd5;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
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

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "FileChunkDO{" +
                "id='" + id + '\'' +
                ", sliceMd5='" + sliceMd5 + '\'' +
                ", md5='" + md5 + '\'' +
                ", sliceIndex=" + sliceIndex +
                ", fileId='" + fileId + '\'' +
                ", completed=" + completed +
                '}';
    }
}
