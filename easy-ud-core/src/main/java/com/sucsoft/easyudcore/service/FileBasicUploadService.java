package com.sucsoft.easyudcore.service;

import com.sucsoft.easyudcore.bean.FileResponse;
import com.sucsoft.easyudcore.bean.FileUploadStatus;
import com.sucsoft.easyudcore.exception.FileUploadException;
import com.sucsoft.easyudsql.service.FileTypeLimitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.sucsoft.easyudcore.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author: "Chenzx"
 * @date: 2019/9/19 09:32
 * @description:
 */
@Service
public class FileBasicUploadService {

    private FileTypeLimitService fileTypeLimitService;
    @Value("${ezUd.fileUpload.folder}")
    private String filePath;
    /**
     * @return:
     * @author: ChenZx
     * @description: 存储上传的文件信息，key为文件的md5值，value为文件信息FileResponse
     * @date: 2019/9/20 11:34
     */
    public Map<String, FileResponse> fileInfoMap = new HashMap<>();

    /**
     * @return:
     * @author: ChenZx
     * @description: 基本文件上传
     * @date: 2019/9/20 13:08
     */
    public FileResponse upload(MultipartFile file, String uploadDir) throws FileUploadException{
        FileResponse fileResponse;
        String originalFileName = file.getOriginalFilename();
        Integer lastIndexOfDot = originalFileName.lastIndexOf(".");
        if (!fileTypeLimitService.fileAllowable(originalFileName) && lastIndexOfDot!=-1) {
            throw new FileUploadException("上传错误：文件类型和后缀名不匹配");
        }
        //文件后缀
        String suffix = file.getOriginalFilename().substring(lastIndexOfDot);
        //文件名除去后缀
        String fileName = file.getOriginalFilename().substring(0, lastIndexOfDot);
        //文件由随机生成的id做服务器端文件名，防止重名文件出现
        String id = UUID.randomUUID().toString();
        try {
            //TODO 字符串拼接效率
            String path = filePath + uploadDir + File.separator + id + suffix;
            fileResponse = realUpload(path, fileName, file);
            //保存成功上传的文件信息
            fileInfoMap.put(fileResponse.getId(), fileResponse);
        }
        //异常处理
        catch (IOException e) {
            e.printStackTrace();
            fileResponse = new FileResponse(fileName, filePath, null, FileUploadStatus.FILE_UPLOAD_STATUS_FAIL);
        }
        return fileResponse;
    }

    /**
     * @return:
     * @author: ChenZx
     * @description:
     * @date: 2019/9/24 15:33
     */
    public FileResponse realUpload(String path, String fileName, MultipartFile file) throws IOException {
        File dest = new File(path);
        // 检测父目录是否存在，不存在则创建父目录
        checkParentDir(dest);
        file.transferTo(dest);
        //获得文件的md5值
        String md5 = FileUtil.getFileMD5(dest);
        FileResponse fileResponse = new FileResponse(fileName, path, md5, FileUploadStatus.FILE_UPLOAD_STATUS_SUC);
        fileResponse.setId(UUID.randomUUID().toString());
        return fileResponse;
    }

    /**
     * @return:
     * @author: ChenZx
     * @description: 上传多个文件
     * @date: 2019/9/20 13:09
     */
    public List<FileResponse> uploadFiles(List<MultipartFile> files, String uploadDir) throws FileUploadException {
        ArrayList<FileResponse> fileResponses = new ArrayList<>();
        for (MultipartFile file : files) {
            fileResponses.add(upload(file, uploadDir));
        }
        return fileResponses;
    }

    /**
     * @return:
     * @author: ChenZx
     * @description: 检查文件的父目录是否存在
     * @date: 2019/9/20 13:09
     */
    public boolean checkParentDir(File path) {
        //父目录是否存在
        if (!path.getParentFile().exists()) {
            // 若不存在则新建目录
            return path.getParentFile().mkdirs();
        } else {
            return true;
        }
    }
}
