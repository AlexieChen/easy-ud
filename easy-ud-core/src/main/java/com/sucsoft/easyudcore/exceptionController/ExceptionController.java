package com.sucsoft.easyudcore.exceptionController;

import com.sucsoft.easyudcore.exception.FileStorageException;
import com.sucsoft.easyudcore.exception.FileUploadException;
import com.sucsoft.easyudcore.exception.MyFileNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
     * @description:   下载找不到文件时异常处理
     * @date: 2019/10/8 11:47
     */
    @ResponseBody
    @ExceptionHandler(value= MyFileNotFoundException.class)
    public Map errorHandler(MyFileNotFoundException ex) {
        //TODO 日志记录
        //initialCapacity = (需要存储的元素个数 / 负载因子(一般为0.75)) + 1
        Map<String,Object> map = new HashMap<>(6);
        map.put("msg",ex.getMessage());
        map.put("cause",ex.getCause());
        return map;
    }

    /**
     * @return:
     * @author: ChenZx
     * @description: 文件上传的异常处理
     * @date: 2019/10/8 14:22
     */
    @ResponseBody
    @ExceptionHandler(value = FileUploadException.class)
    public Map errorHandler(FileUploadException ex){
        Map<String,Object> map = new HashMap<>(6);
        map.put("msg",ex.getMessage());
        map.put("cause",ex.getCause());
        return map;
    }

    /**
     * @return:
     * @author: ChenZx
     * @description: 文件存储的异常处理，目前没用
     * @date: 2019/10/8 14:23
     */
    @ResponseBody
    @ExceptionHandler(value = FileStorageException.class)
    public Map errorHandler(FileStorageException ex){
        Map<String,Object> map = new HashMap<>(6);
        map.put("msg",ex.getMessage());
        map.put("cause",ex.getCause());
        return map;
    }
}
