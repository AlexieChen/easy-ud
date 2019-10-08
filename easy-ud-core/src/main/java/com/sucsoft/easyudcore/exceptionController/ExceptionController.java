package com.sucsoft.easyudcore.exceptionController;

import com.sucsoft.easyudcore.exception.FileStorageException;
import com.sucsoft.easyudcore.exception.FileUploadException;
import com.sucsoft.easyudcore.exception.MyFileNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: "Chenzx"
 * @Date: 2019/10/8 11:10
 * @Description:
 */
@ControllerAdvice
public class ExceptionController {

    private static Logger logger = LogManager.getLogger(ExceptionController.class.getName());

    /**
     * @return:
     * @author: ChenZx
     * @description: 下载找不到文件时异常处理
     * @date: 2019/10/8 11:47
     */
    @ResponseBody
    @ExceptionHandler(MyFileNotFoundException.class)
    public ResponseEntity<MyFileNotFoundException> errorHandler(MyFileNotFoundException ex) {
        //TODO 日志记录
        logger.debug(ex.getMessage());
        return ResponseEntity.badRequest().body(ex);
    }

    /**
     * @return:
     * @author: ChenZx
     * @description: 文件上传的异常处理
     * @date: 2019/10/8 14:22
     */
    @ResponseBody
    @ExceptionHandler({FileUploadException.class, FileStorageException.class})
    public ResponseEntity errorHandler(FileUploadException ex) {
        logger.debug(ex.getMessage());
        return ResponseEntity.badRequest().body(ex);
    }

    /**
     * @return:
     * @author: ChenZx
     * @description: 文件存储的异常处理，目前没用
     * @date: 2019/10/8 14:23
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity errorHandler(Exception ex) {
        logger.error(ex.getMessage());
        return ResponseEntity.badRequest().body(ex);
    }

    /**
     * @return:
     * @author: ChenZx
     * @description: 解码方式不支持
     * @date: 2019/10/8 18:04
     */
    @ResponseBody
    @ExceptionHandler(value = UnsupportedEncodingException.class)
    public ResponseEntity errorHandler(UnsupportedEncodingException ex) {
        logger.error(ex.getMessage());
        return ResponseEntity.badRequest().body(ex);
    }
}
