package com.sucsoft.easyudcore.service;

import com.sucsoft.easyudcore.bean.FileDownloadDO;
import com.sucsoft.easyudcore.bean.FileResponse;
import com.sucsoft.easyudcore.util.FilePathUtil;
import com.sucsoft.easyudexception.exception.MyFileNotFoundException;
import com.sucsoft.easyudcore.util.MultiPartFileUtil;
import org.apache.tomcat.util.buf.ByteChunk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import java.io.*;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author: "Chenzx"
 * @Date: 2019/9/25 09:57
 * @Description:
 */
@Service
public class FIleBasicDownloadService {
    Logger logger = LoggerFactory.getLogger(FIleBasicDownloadService.class);
    @Value("${ezUd.fileUpload.folder}")
    private String filePath;
    @Value("${ezUd.fileDownload.folder}")
    private String downFolder;
    @Autowired
    private FileBasicUploadService fileBasicUploadService;

    /**
     * @return:
     * @author: ChenZx
     * @description: 基础下载功能
     * @date: 2019/9/25 19:23
     */
    public ResponseEntity<Resource> realDownload(String fileUri, String fileName) throws MyFileNotFoundException, UnsupportedEncodingException {
        File file = new File(fileUri);
        if (!file.exists()) {
            throw new MyFileNotFoundException("找不到对应文件" + ":" + fileUri);
        }
        //文件名后缀
        String fileSuffix = MultiPartFileUtil.fileSuffix(fileUri);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource("file:" + fileUri);
        MediaType mediaType = new MediaType("application", "x-download", Charset.forName("utf-8"));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(fileName + fileSuffix, "utf-8") + "\"");
        //httpHeaders.add(HttpHeaders.CONTENT_RANGE,"$start +");
        return ResponseEntity.ok()
                .contentType(mediaType)
                .headers(httpHeaders)
                //.header(HttpHeaders.ACCEPT_RANGES,"100-2000")
                .body(resource);
    }

    public ResponseEntity downloadFile(String id) throws MyFileNotFoundException, UnsupportedEncodingException {
        String fileUri = fileBasicUploadService.fileInfoMap.get(id).getUploadPath();
        String fileName = fileBasicUploadService.fileInfoMap.get(id).getFileName();
        return realDownload(fileUri, fileName);
    }

    public List<ResponseEntity> asyncDownloadFile(String id) throws MyFileNotFoundException {
        FileResponse fileInfo = fileBasicUploadService.fileInfoMap.get(id);
        String fileUri = fileInfo.getUploadPath();
        String fileName = fileBasicUploadService.fileInfoMap.get(id).getFileName();
        List<ResponseEntity> responseEntities = new ArrayList<ResponseEntity>();
        int size = 1024 * 1024 * 10;
        try {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource resource = resolver.getResource("file:" + fileUri);
            long fileSize = resource.getFile().length();
            long sliceCount = fileSize % size > 0 ? fileSize / size + 1 : fileSize / size;
            for (int i = 0; i < sliceCount; i++) {
                responseEntities.add(asyncDownload(id, Long.valueOf(i), size));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseEntities;
    }


    /**
     * @return : org.springframework.http.ResponseEntity<org.springframework.core.io.Resource>
     * @author : ChenZx
     * @description : asyncDownload
     * @param  fileId:
     * @date : 2019/11/28 10:18
     */
    @Async
    public ResponseEntity<Resource> asyncDownload(String fileId, Long index, int size) throws UnsupportedEncodingException {
        String fileUri = fileBasicUploadService.fileInfoMap.get(fileId).getUploadPath();
        String id = String.valueOf(System.currentTimeMillis());
        //未分片的原始文件
        FileDownloadDO result = new FileDownloadDO(id, Integer.valueOf(index.toString()), fileUri, false);
        try {
            ByteArrayResource byteArrayResource;
            RandomAccessFile sourceFile = new RandomAccessFile(fileUri, "r");
            Long start = index * size;
            String fileName = FilePathUtil.fileNameWithoutExtension(fileUri);
            String extension = FilePathUtil.fileExtension(fileUri);
            logger.info("开始下载 序号为： " + index + "的分片文件");
            //定位到startPos
            FileChannel inputChannel = sourceFile.getChannel().position(start);
            ByteBuffer byteBuffer = ByteBuffer.allocate(size);
            inputChannel.read(byteBuffer);
            byteArrayResource = new ByteArrayResource(byteBuffer.array());
            result.setCompleted(true);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(fileName + extension, "utf-8") + "\"");
            httpHeaders.add(HttpHeaders.CONTENT_RANGE, start + "-" + (start + size - 1));
            MediaType mediaType = new MediaType("application", "x-download", Charset.forName("utf-8"));
            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .headers(httpHeaders)
                    .body(byteArrayResource);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(index + "  序号分片下载失败");
            result.setCompleted(false);
        }
        return ResponseEntity.badRequest().build();
    }


    /**
     * 文件恢复下载，需要支持HTTP 1.1
     *
     * @param fileId  : 文件id
     * @param startPos : 流开始位置
     * @param endPos : 流结束位置
     * @return : org.springframework.http.ResponseEntity<org.springframework.core.io.Resource>
     * @author : ChenZx
     * @date : 2019/11/27 16:19
     */
    public ResponseEntity<Resource> resumeDownload(String fileId, Long startPos, Long endPos) {
        //文件路径
        String uploadPath = fileBasicUploadService.fileInfoMap.get(fileId).getUploadPath();
        //TODO 恢复下载失败
        FileDownloadDO task = new FileDownloadDO();
        FileChannel inputChannel;
        ByteArrayResource byteArrayResource;
        try {
            //读取文件
            RandomAccessFile originalFile = new RandomAccessFile(uploadPath, "r");
            //实际的结束位置
            Long realEndPos = endPos != null ? endPos : originalFile.length();
            //定位到startPos
            inputChannel = originalFile.getChannel().position(startPos);
            //缓冲区大小,需要小于Integer.MAX_VALUE
            Long capacity = realEndPos - startPos + 1;
            ByteBuffer byteBuffer = ByteBuffer.allocate(capacity.intValue());
            //读取
            inputChannel.read(byteBuffer);
            //TODO 需要传输的流字节数大于Integer.MAX_VALUE时改用InputStreamResource
            byteArrayResource = new ByteArrayResource(byteBuffer.array());
            logger.info("开始传输 范围为： " + startPos + "-" + realEndPos + "的分片文件");
            HttpHeaders httpHeaders = new HttpHeaders();
            //设置响应头
            httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
            //CONTENT_RANGE: bytes 开始字节位置 -结束字节位置/总字节数
            httpHeaders.add(HttpHeaders.CONTENT_RANGE, "bytes " + startPos + "-" + realEndPos + "/" + originalFile.length());
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                    .headers(httpHeaders)
                    .body(byteArrayResource);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("范围为： " + startPos + "-" + endPos + " 的分片下载失败");
            return ResponseEntity.badRequest().build();
        }
    }

}
