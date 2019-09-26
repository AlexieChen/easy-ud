package com.sucsoft.easyudcore.service;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * @Author: "Chenzx"
 * @Date: 2019/9/26 10:53
 * @Description:
 */
public class FileLoadService {
    /**
     * @return:
     * @author: ChenZx
     * @description: 文件读取
     * @date: 2019/9/26 11:15
     */
    public void loadFile(String fileUri, HttpServletResponse response) throws IOException {
        File file = new File(fileUri);
        if (!file.exists()) {
            throw new IOException("在服务器找不到对应文件");
        }
        FileInputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = response.getOutputStream();
        try {
            response.reset();
            //contentType目前用二进制流，前端自行转换
            //有些文件类型，前端无法预览
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
}
