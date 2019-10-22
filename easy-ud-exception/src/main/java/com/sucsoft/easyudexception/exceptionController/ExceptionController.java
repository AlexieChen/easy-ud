package com.sucsoft.easyudexception.exceptionController;

import com.sucsoft.easyudexception.exception.FileStorageException;
import com.sucsoft.easyudexception.exception.FileUploadException;
import com.sucsoft.easyudexception.exception.MyFileNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;


/**
 * @Author: "Chenzx"
 * @Date: 2019/10/8 11:10
 * @Description:
 */
@ControllerAdvice
public class ExceptionController {

    private static Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    /**
     * @return:
     * @author: ChenZx
     * @description: 下载找不到文件时异常处理
     * @date: 2019/10/8 11:47
     */
    @ResponseBody
    @ExceptionHandler(MyFileNotFoundException.class)
    public ResponseEntity<MyFileNotFoundException> errorHandler(MyFileNotFoundException ex) {
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
