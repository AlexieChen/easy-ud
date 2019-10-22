package com.sucsoft.easyudcore.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author : "Chenx"
 * @date : 2019/9/23 16:57
 */
@ApiModel("上传文件后返给前端的对象")
public class FileChunkUploadResponse {
    @ApiModelProperty("上传状态")
    private int uploadStatus ;
    @ApiModelProperty("下一个分块的索引")
    private int nextIndex;

    public int getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(int uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public int getNextIndex() {
        return nextIndex;
    }

    public void setNextIndex(int nextIndex) {
        this.nextIndex = nextIndex;
    }
    public FileChunkUploadResponse(){
    }

    public FileChunkUploadResponse(int uploadStatus, int nextIndex) {
        this.uploadStatus = uploadStatus;
        this.nextIndex = nextIndex;
    }
}
