package com.sucsoft.easyudcore.service;

import com.sucsoft.easyudcore.util.FileUtil;
import com.sucsoft.easyudexception.exception.MyFileNotFoundException;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author :
 * @date : 2019/10/21 15:22
 */
@Service
public class FileStorageService {
    //TODO 大文件拼接
    //TODO 多线程下载
    //TODO 改用nio
    /**
     * *  文件大小
     **/
    private int fileSizeKb = 1024;
    private int fileSizeMb = 1024 * 1024;

    /**
     * @param chunkPath: 分片的绝对路径
     * @param targetPath : 目标文件的路径
     * @param start      : 写入的开始位置
     * @author : ChenZx
     * @date : 2019/10/21 15:22
     */
    public void writeChunk(String chunkPath, String targetPath, int start) throws IOException {
        SeekableByteChannel outChannel = FileChannel.open(Paths.get(targetPath), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        SeekableByteChannel inChannel = FileChannel.open(Paths.get(chunkPath), StandardOpenOption.READ);
        try {
            //文件写入位置
            outChannel.position(start);
            //long转换成int
            if (inChannel.size() >  (long) Integer.MAX_VALUE)     {
                throw new IOException("分片文件大小超过了int最大值");
            }
            ByteBuffer bytes = ByteBuffer.allocate((int) inChannel.size());
            //读取分片文件
            inChannel.read(bytes);
            bytes.flip();
            //写入
            outChannel.write(bytes);
            bytes.clear();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new MyFileNotFoundException("找不到文件,文件路径" + chunkPath);
        } finally {
            outChannel.close();
            inChannel.close();
        }
    }

    /**
     * 创建指定大小的文件
     *
     * @param targetPath : 目标文件的路径
     * @param size       : 写入的开始位置
     * @author : ChenZx
     * @date : 2019/10/21 15:22
     */
    public void createTargetFile(String targetPath, long size) throws IOException {
        FileUtil.createFileWithSize(targetPath, size);
    }

}
