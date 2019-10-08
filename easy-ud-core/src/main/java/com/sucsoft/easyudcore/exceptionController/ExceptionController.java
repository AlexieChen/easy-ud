package com.sucsoft.easyudcore.exceptionController;

import com.sucsoft.easyudcore.exception.FileStorageException;
import com.sucsoft.easyudcore.exception.FileUploadException;
import com.sucsoft.easyudcore.exception.MyFileNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: "Chenzx"
 * @Date: 2019/10/8 11:10
 * @Description:
 */
@ControllerAdvice
public class ExceptionController {
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
        return ResponseEntity.badRequest().body(ex);
    }

    /**
     * @return:
     * @author: ChenZx
     * @description: 文件上传的异常处理
     * @date: 2019/10/8 14:22
     */
    @ResponseBody
    @ExceptionHandler({FileUploadException.class,FileStorageException.class})
    public ResponseEntity errorHandler(FileUploadException ex) {
        return ResponseEntity.badRequest().body(ex);
    }

    /**
     * @return:
     * @author: ChenZx
     * @description: 文件存储的异常处理，目前没用
     * @date: 2019/10/8 14:23
     */
/*    @ResponseBody
    @ExceptionHandler(value = FileStorageException.class)
    public ResponseEntity errorHandler(FileStorageException ex) {
        return ResponseEntity.badRequest().body(ex);
    }*/
}
