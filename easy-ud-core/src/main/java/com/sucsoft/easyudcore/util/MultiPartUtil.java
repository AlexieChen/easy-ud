package com.sucsoft.easyudcore.util;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: "Chenzx"
 * @Date: 2019/10/8 15:30
 * @Description:
 */
public class MultiPartUtil {

    public static Integer lastIndexOfDot(MultipartFile file) {
        return file.getOriginalFilename().lastIndexOf(".");
    }

    public static String fileSuffix(MultipartFile file) {
        Integer lastIndexOfDot = lastIndexOfDot(file);
        //包含“."
        return file.getOriginalFilename().substring(lastIndexOfDot);
    }

    public static String fileName(MultipartFile file) {
        Integer lastIndexOfDot = lastIndexOfDot(file);
        return file.getOriginalFilename().substring(0, lastIndexOfDot);
    }

}
