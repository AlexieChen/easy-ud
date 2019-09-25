package com.sucsoft.easyudcore.util;


import com.sucsoft.easyudcore.bean.FileChunkDO;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: "Chenzx"
 * @date: 2019/9/18 14:30
 * @description:文件工具类
 */
public class FileUtil {
    /**
     * @return:
     * @author: ChenZx
     * @param: file-文件
     * @description: 获取文件md5值
     * @date: 2019/9/19 13:28
     */
    public static String getFileMD5(File file) {
        int bufferSize = 1024;
        if (!file.exists() || !file.isFile()) {
            return null;
        }
        MessageDigest digest;
        FileInputStream in;
        byte[] buffer = new byte[bufferSize];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, bufferSize)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }
    public static  List<String> getChunkMd5(List<FileChunkDO> chunks, Boolean completed){
        Stream<String> x=chunks.stream().filter(chunk->chunk.getCompleted().equals(completed)).map(FileChunkDO::getMd5);
        return x.collect(Collectors.toList());
    }

    public static  List<Integer> getSliceIndexs(List<FileChunkDO> slices, Boolean completed){
        Stream<Integer> x=slices.stream().filter(slice->slice.getCompleted().equals(completed)).map(FileChunkDO::getSliceIndex);
        return x.collect(Collectors.toList());
    }

}
