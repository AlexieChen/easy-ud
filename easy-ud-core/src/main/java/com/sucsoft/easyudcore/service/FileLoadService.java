package com.sucsoft.easyudcore.service;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: "Chenzx"
 * @Date: 2019/9/26 10:53
 * @Description:
 */
@Service
public class FileLoadService {
    @Value("${ezUd.fileUpload.folder}")
    private String filePath;
    @Autowired
    private FileBasicUploadService fileBasicUploadService;

    /**
     * @return:
     * @author: ChenZx
     * @description: 文件读取
     * @date: 2019/9/26 11:15
     */
    public void realLoadFile(String fileUri, HttpServletResponse response) throws IOException {
        //前端预览文件
        File file = new File(fileUri);
        if (!file.exists()) {
            throw new IOException("在服务器找不到对应文件");
        }
        FileInputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = response.getOutputStream();
        try {
            response.reset();
            //contentType目前用二进制流，前端自行转换
            //TODO 有些文件类型，浏览器无法预览（限制）
            response.setContentType("application/octet-stream");
            IOUtils.copy(inputStream, outputStream);
            response.flushBuffer();
        } catch (IOException e) {
            response.sendError(404, "在服务器找不到相应文件");
        } finally {
            outputStream.flush();
            outputStream.close();
        }
    }

    public void loadFile(String id, HttpServletResponse response) throws IOException {
        String fileUri = fileBasicUploadService.fileInfoMap.get(id).getUploadPath();
        realLoadFile(fileUri, response);
    }
}
