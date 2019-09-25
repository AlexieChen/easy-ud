package com.sucsoft.easyudsql.service;

import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

/**
 * 限制文件上传类型
 *
 * @author YangJJ
 * @date 2019/9/25 10:03
 */
@Service
public class FileTypeLimitService {
    /**
     * 缓存文件头信息-文件头信息
     */
    private static final HashMap<String, String> M_FILE_TYPES = new HashMap<>();
    private static final Integer MAX_FILE_HEAD_SIZE = 28;

    static {
        // images
        M_FILE_TYPES.put("FFD8FF", "jpg");
        M_FILE_TYPES.put("89504E47", "png");
        M_FILE_TYPES.put("47494638", "gif");
        M_FILE_TYPES.put("49492A00", "tif");
        M_FILE_TYPES.put("424D", "bmp");
        // CAD
        M_FILE_TYPES.put("41433130", "dwg");
        M_FILE_TYPES.put("38425053", "psd");
        // 日记本
        M_FILE_TYPES.put("7B5C727466", "rtf");
        M_FILE_TYPES.put("3C3F786D6C", "xml");
        M_FILE_TYPES.put("68746D6C3E", "html");
        // 邮件
        M_FILE_TYPES.put("44656C69766572792D646174653A", "eml");
        M_FILE_TYPES.put("D0CF11E0", "doc");
        M_FILE_TYPES.put("5374616E64617264204A", "mdb");
        M_FILE_TYPES.put("252150532D41646F6265", "ps");
        M_FILE_TYPES.put("255044462D312E", "pdf");
        M_FILE_TYPES.put("504B0304", "zip");
        M_FILE_TYPES.put("52617221", "rar");
        M_FILE_TYPES.put("57415645", "wav");
        M_FILE_TYPES.put("41564920", "avi");
        M_FILE_TYPES.put("2E524D46", "rm");
        M_FILE_TYPES.put("000001BA", "mpg");
        M_FILE_TYPES.put("000001B3", "mpg");
        M_FILE_TYPES.put("6D6F6F76", "mov");
        M_FILE_TYPES.put("3026B2758E66CF11", "asf");
        M_FILE_TYPES.put("4D546864", "mid");
        M_FILE_TYPES.put("1F8B08", "gz");
        M_FILE_TYPES.put("4D5A9000", "exe/dll");
        M_FILE_TYPES.put("75736167", "txt");
    }

    /**
     * 根据文件路径获取文件头信息
     *
     * @param filePath 文件路径
     * @return 文件头信息
     */
    public Boolean fileAllowable(String filePath) {
        String fileHead = getFileHeader(filePath);
        Set<String> fileTypes = M_FILE_TYPES.keySet();
        for (String type : fileTypes) {
            if (fileHead.startsWith(type)) {
                System.out.println(type);
                return true;
            }
        }
        System.out.println(fileHead);
        return false;
    }

    /**
     * 根据文件路径获取文件头信息
     *
     * @param filePath 文件路径
     * @return 文件头信息
     */
    public String getFileHeader(String filePath) {
        FileInputStream is = null;
        String value = null;
        try {
            is = new FileInputStream(filePath);
            byte[] b = new byte[MAX_FILE_HEAD_SIZE];
            /*
             * int read() 从此输入流中读取一个数据字节。 int read(byte[] b) 从此输入流中将最多 b.length
             * 个字节的数据读入一个 byte 数组中。 int read(byte[] b, int off, int len)
             * 从此输入流中将最多 len 个字节的数据读入一个 byte 数组中。
             */
            is.read(b);
            value = bytesToHexString(b);
        } catch (Exception e) {
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
        return value;
    }

    /**
     * 将要读取文件头信息的文件的byte数组转换成string类型表示
     *
     * @param src 要读取文件头信息的文件的byte数组
     * @return 文件头信息
     */
    public String bytesToHexString(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        String hv;
        for (int i = 0; i < src.length; i++) {
            // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
            hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        return builder.toString();
    }
}
