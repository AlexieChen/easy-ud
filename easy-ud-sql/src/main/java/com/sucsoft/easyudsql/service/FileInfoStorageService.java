package com.sucsoft.easyudsql.service;

import com.sucsoft.easyudsql.bean.FileInfo;
import com.sucsoft.easyudsql.repository.FileInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文件增删改查
 *
 * @author YangJJ
 * @date 2019/9/20 11:20
 */
@Service
public class FileInfoStorageService {
    @Autowired
    private FileInfoMapper fileInfoMapper;

    /**
     * @implNote 所有文件信息
     */
    public List<FileInfo> queryAllFileInfo() {
        return fileInfoMapper.selectAll();
    }

    /**
     * @implNote 通过Id查找对应文件信息
     */
    public FileInfo queryFileInfoById(String id) {
        return fileInfoMapper.selectById(id);
    }

    /**
     * @implNote 通过md5值查找对应文件信息
     */
    public FileInfo queryFileInfoByMd5(String md5) {
        return fileInfoMapper.selectByMd5(md5);
    }

    /**
     * @implNote 保存文件信息
     */
    public void saveFileInfo(FileInfo fileInfo) {
        fileInfoMapper.save(fileInfo);
    }

    /**
     * @implNote 更新文件信息
     */
    public void updateFileInfo(FileInfo fileInfo) {
        fileInfoMapper.update(fileInfo);
    }
}
