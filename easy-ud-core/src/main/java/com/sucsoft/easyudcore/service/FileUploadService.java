package com.sucsoft.easyudcore.service;

import com.sucsoft.easyudcore.bean.*;
import com.sucsoft.easyudexception.exception.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.sucsoft.easyudcore.util.FileUtil;

import java.io.IOException;
import java.util.*;

/**
 * 文件上传服务
 *
 * @author : ChenZx
 * @date : 2019/9/19 15:34
 */
@Service
public class FileUploadService {
    @Autowired
    private FileBasicUploadService fileBasicUploadService;

    @Autowired
    private FileStorageService fileStorageService;

    public Map<String, ArrayList<FileChunkDO>> FileChunkInfo;

    /**
     * 文件上传，若已经上传过，直接返回文件信息
     *
     * @return :
     * @author : ChenZx
     * @date : 2019/9/20 13:10
     */
    public FileResponse upload(String md5, String uploadDir, MultipartFile file) throws FileUploadException {
        if (uploadedBefore(md5)) {
            return fileBasicUploadService.fileInfoMap.get(md5);
        } else {
            return fileBasicUploadService.upload(file, uploadDir);
        }
    }

    /**
     * 通过md5值检验文件是否上传过 , 待添加数据库验证
     *
     * @return  :
     * @author : ChenZx
     * @date : 2019/9/20 13:13
     */
    public boolean uploadedBefore(String md5) {
        return fileBasicUploadService.fileInfoMap.containsKey(md5);
    }

    /**
     * 分片上传
     *
     * @return :
     * @author : ChenZx
     * @date : 2019/9/20 13:49
     */
    public FileChunkUploadResponse uploadFileSlice(MultipartFile chunk, FileForm fileForm, String uploadDir) throws IOException {
        FileChunkUploadResponse responseVo;
        if (fileForm.getType().equals(FileFormType.CHECK_FILE_STATUS)) {
            responseVo = checkFileStatus(fileForm);
        } else if (fileForm.getType().equals(FileFormType.UPLOAD_FILE_ONLY)) {
            responseVo = uploadChunkFIle(chunk, fileForm, uploadDir);
        } else {
            //TODO 其他类型表单处理
            responseVo = new FileChunkUploadResponse();
        }
        return responseVo;
    }
    //TODO 上传和检查要不要分开

    /**
     *
     * 1. 文件状态：上传成功/上传失败；上传成功时nextIndex，当前分块的index加1，即略过当前分块，直接上传下一分块；
     * 上传失败时，nextIndex为当前分块的index，即上传当前分块；当nextIndex大于总分片大小时，上传完成
     *
     * @return :
     * @author : ChenZx
     * @date : 2019/9/23 17:45
     */
    public FileChunkUploadResponse checkFileStatus(FileForm fileForm) {
        //返回给前端的表单
        FileChunkUploadResponse responseVo ;
        //文件的md5值
        String md5 = fileForm.getMd5();
        if (FileChunkInfo.keySet().contains(md5)) {
            //获取已经上传成功的分片文件中的md5值
            List<FileChunkDO> slices = FileChunkInfo.get(md5);
            List<String> chunkMd5s = FileUtil.getChunksMd5(slices, Boolean.TRUE);
            //上传成功的分片的索引值
            List<Integer> chunkIndexs = FileUtil.getSliceIndexes(slices, Boolean.TRUE);
            //该分块是否已上传成功
            if (chunkIndexs.contains(fileForm.getChunkIndex())) {
                //文件已经上传成功
                responseVo = new FileChunkUploadResponse(FileUploadStatus.FILE_UPLOAD_STATUS_SUC, fileForm.getChunkIndex() + 1);
            } else {
                //文件上传失败或未上传
                responseVo = new FileChunkUploadResponse(FileUploadStatus.FILE_UPLOAD_STATUS_FAIL, fileForm.getChunkIndex());
            }
        } else {
            responseVo = new FileChunkUploadResponse(FileUploadStatus.FILE_UPLOAD_STATUS_FAIL, fileForm.getChunkIndex());
        }
        return responseVo;
    }

    /**
     * 上传文件分片
     *
     * @return :
     * @param chunk : 文件分片
     * @param fileForm : 上传文件的表单
     * @param  chunkPath :文件分片的存储路径
     * @author : ChenZx
     * @date : 2019/9/24 10:43
     */
    public FileChunkUploadResponse uploadChunkFIle(MultipartFile chunk, FileForm fileForm, String chunkPath) {
        FileChunkUploadResponse responseVo;
        try {
            //TODO 断点续传，无数据库时
            //String chunkUploadDir =
            fileBasicUploadService.realUpload(chunkPath, chunk);
            responseVo = new FileChunkUploadResponse(FileUploadStatus.FILE_UPLOAD_STATUS_SUC, fileForm.getChunkIndex() + 1);
            //TODO 抛出异常，处理异常的代码块中实现拼接，拼接-任务队列
        } catch (IOException ex) {
            ex.printStackTrace();
            responseVo = new FileChunkUploadResponse(FileUploadStatus.FILE_UPLOAD_STATUS_FAIL, fileForm.getChunkIndex());
        }
        return responseVo;
    }
}
