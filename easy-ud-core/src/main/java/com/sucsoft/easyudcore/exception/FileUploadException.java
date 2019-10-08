package com.sucsoft.easyudcore.exception;

/**
 * @Author: "Chenzx"
 * @Date: 2019/9/19 11:38
 * @Description:
 */
public class FileUploadException extends Exception {

    public FileUploadException(String message) {
        super(message);
    }

    public FileUploadException(String message, Throwable cause) {
        super(message, cause);
    }
}
