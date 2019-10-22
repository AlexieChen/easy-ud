package com.sucsoft.easyudcore.util;


import com.sucsoft.easyudcore.bean.FileChunkDO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 文件工具类
 * @author : "Chenzx"
 * @date : 2019/9/18 14:30
 */
public class FileUtil {
    /**
     * 获取文件md5值
     * @param file : 文件
     * @author : ChenZx
     * @date : 2019/9/19 13:28
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

    public static List<String> getChunkMd5(List<FileChunkDO> chunks, Boolean completed) {
        Stream<String> x = chunks.stream().filter(chunk -> chunk.getCompleted().equals(completed)).map(FileChunkDO::getMd5);
        return x.collect(Collectors.toList());
    }

    public static List<Integer> getSliceIndexs(List<FileChunkDO> slices, Boolean completed) {
        Stream<Integer> x = slices.stream().filter(slice -> slice.getCompleted().equals(completed)).map(FileChunkDO::getSliceIndex);
        return x.collect(Collectors.toList());
    }
    /**
     * 创建指定大小的文件
     * @param filePath : 文件路径
     * @param  fileSize: 文件大小
     * @author : ChenZxGiven
     * @date : 2019/10/22 13:28
     */
    public static void createFileWithSize(String filePath, Long fileSize) throws IOException {
        Path path = Paths.get(filePath);
        try {
            Files.deleteIfExists(path);
            try (
                    RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "rw");
            ) {
                randomAccessFile.setLength(fileSize);
            }
        } catch (IOException e) {
            throw new IOException("文件创建失败,文件路径:" + filePath + " 文件大小:" + fileSize, e);
        }
    }

    /**
     * 删除文件或文件夹
     * @param file : 被删除的文件或文件夹
     * @author : ChenZx
     * @date : 2019/10/22 13:28
     */
    public static void deleteIfExists(File file) throws IOException {
        if (file.exists()) {
            if (file.isFile()) {
                if (!file.delete()) {
                    throw new IOException("删除文件失败,路径:" + file.getAbsolutePath());
                }
            } else {
                File[] files = file.listFiles();
                if (files != null && files.length > 0) {
                    for (File temp : files) {
                        deleteIfExists(temp);
                    }
                }
                if (!file.delete()) {
                    throw new IOException("删除文件夹失败,路径:" + file.getAbsolutePath());
                }
            }
        }
    }
    /**
     * 删除文件或文件夹
     */
    public static void deleteIfExists(String path) throws IOException {
        deleteIfExists(new File(path));
    }


}
