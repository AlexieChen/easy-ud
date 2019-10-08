package com.sucsoft.easyudcore.service;

import com.sucsoft.easyudcore.exception.MyFileNotFoundException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.Charset;

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
    public ResponseEntity<Resource> realDownload(String fileUri, String fileName) throws MyFileNotFoundException,UnsupportedEncodingException {
        File file = new File(fileUri);
        if (!file.exists()) {
            throw new MyFileNotFoundException("找不到对应文件" + ":" + fileUri);
        }
        //文件名
        //TODO 用Resource框架写，需要整理一下
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource("file:"+fileUri);
        MediaType mediaType = new MediaType( "application","x-download",Charset.forName("utf-8"));
        return ResponseEntity.ok()
                .contentType(mediaType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +URLEncoder.encode(fileName, "utf-8")+ "\"")
                .body(resource);
    }

    public ResponseEntity downloadFile(String id) throws MyFileNotFoundException ,UnsupportedEncodingException{
        String fileUri = fileBasicUploadService.fileInfoMap.get(id).getUploadPath();
        String fileName = fileBasicUploadService.fileInfoMap.get(id).getFileName();
        return realDownload(fileUri, fileName);
    }
}
