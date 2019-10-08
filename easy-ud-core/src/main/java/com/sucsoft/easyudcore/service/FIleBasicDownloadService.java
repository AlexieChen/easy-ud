package com.sucsoft.easyudcore.service;

import com.sucsoft.easyudcore.exception.MyFileNotFoundException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @Author: "Chenzx"
 * @Date: 2019/9/25 09:57
 * @Description:
 */
@Service
public class FIleBasicDownloadService {
    @Value("${ezUd.fileUpload.folder}")
    private String filePath;
    @Autowired
    private FileBasicUploadService fileBasicUploadService;

    /**
     * @return:
     * @author: ChenZx
     * @description: 基础下载功能
     * @date: 2019/9/25 19:23
     */
    public void realDownload(String fileUri, HttpServletResponse response) throws IOException, MyFileNotFoundException {
        File file = new File(fileUri);
        if (!file.exists()) {
            throw new MyFileNotFoundException("找不到对应文件"+":"+fileUri);
        }
        FileInputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = response.getOutputStream();
        try {
            //文件名
            //TODO 用Resource框架写，需要整理一下
            String fileName = fileUri.substring(fileUri.lastIndexOf(File.separator) + 1);
            response.reset();
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
            IOUtils.copy(inputStream, outputStream);
            response.flushBuffer();
        } catch (IOException e) {
            //状态码408：请求超时
            response.sendError(HttpServletResponse.SC_REQUEST_TIMEOUT, "发生 I/O 错误：磁盘损坏/资源不可用/资源被占用导致请求超时");
        } finally {
            outputStream.flush();
            outputStream.close();
        }
    }

    public void downloadFile(String id, HttpServletResponse response) throws IOException, MyFileNotFoundException {
        String fileUri = fileBasicUploadService.fileInfoMap.get(id).getUploadPath();
        realDownload(fileUri, response);
    }
}
