package com.sucsoft.easyudcore.util;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * @Author: "Chenzx"
 * @Date: 2019/10/8 16:07
 * @Description:
 */
public class FilePathUtil {
    public static String absolutePath(String uploadPath,String relativePath ,MultipartFile file) {
        String id = UUID.randomUUID().toString();
        return  uploadPath + relativePath + File.separator + id + MultiPartFileUtil.fileSuffix(file);
    }

    public  static  String fileNameWithoutExtension(String fileUri){
        int startIndex = fileUri.lastIndexOf("/");
        int endIndex = fileUri.lastIndexOf(".");
        return fileUri.substring(startIndex+1,endIndex);
    }

    public  static  String fileExtension(String fileUri){
        int startIndex = fileUri.lastIndexOf(".")==-1?0:fileUri.lastIndexOf(".");
        return fileUri.substring(startIndex);
    }

}
