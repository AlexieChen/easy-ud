package com.sucsoft.easyudcore.service;

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
     *
     * @author: ChenZx
     * @description: 基础下载功能
     * @date: 2019/9/25 19:23
     */
    public void realDownload(String fileUri, HttpServletResponse response) throws IOException {
        File file = new File(fileUri);
        if (!file.exists()) {
            throw new IOException("在服务器找不到相应文件");
        }
        FileInputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = response.getOutputStream();
        try {
            //文件名
            String fileName = fileUri.substring(fileUri.lastIndexOf(File.separator) + 1);
            response.reset();
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
            IOUtils.copy(inputStream, outputStream);
            response.flushBuffer();
        } catch (IOException e) {
            response.sendError(404, "在服务器找不到相应文件");
        } finally {
            outputStream.flush();
            outputStream.close();
        }
    }

    public void downloadFile(String id, HttpServletResponse response) throws IOException {
        String fileUri = fileBasicUploadService.fileInfoMap.get(id).getUploadPath();
        realDownload(fileUri, response);
    }
}
