package com.sucsoft.easyudcore.util;

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

}
