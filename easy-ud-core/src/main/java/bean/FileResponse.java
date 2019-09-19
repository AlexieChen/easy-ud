package bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: "Chenzx"
 * @Date: 2019/9/19 13:39
 * @Description:
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
    @ApiModelProperty("报错信息")
    private String message;

    public FileResponse() {
    }

    public FileResponse(String fileName, String uploadPath, String md5, String message) {
        this.fileName = fileName;
        this.uploadPath = uploadPath;
        this.md5 = md5;
        this.message = message;
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

    public String getMd5() { return md5; }

    public void setMd5(String md5) { this.md5 = md5; }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
