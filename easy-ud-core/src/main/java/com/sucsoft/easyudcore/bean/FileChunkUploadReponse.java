package com.sucsoft.easyudcore.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: "Chenzx"
 * @Date: 2019/9/23 16:57
 * @Description:
 */
@ApiModel("上传文件后返给前端的对象")
public class FileChunkUploadReponse {
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
    public FileChunkUploadReponse(){
    }

    public FileChunkUploadReponse(int uploadStatus, int nextIndex) {
        this.uploadStatus = uploadStatus;
        this.nextIndex = nextIndex;
    }
}
