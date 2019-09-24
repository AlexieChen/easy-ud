package com.sucsoft.easyudsql.service;

import com.sucsoft.easyudsql.bean.FileInfo;
import com.sucsoft.easyudsql.repository.FileInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author YangJJ
 * @Date 2019/9/20 11:20
 * @Description
 */
@Service
public class StorageFileInfo {

    @Autowired
    private FileInfoMapper fileInfoMapper;

    /**
     * @Description 所有文件信息
     */
    private List<FileInfo> queryAllFileInfo() {
        return fileInfoMapper.selectAll();
    }

    /**
     * @Description 通过Id查找对应文件信息
     */
    private FileInfo queryFileInfoById(String id) {
        return fileInfoMapper.selectById(id);
    }

    /**
     * @Description 通过md5值查找对应文件信息
     */
    private FileInfo queryFileInfoByMd5(String md5) {
        return fileInfoMapper.selectByMd5(md5);
    }

    /**
     * @Description 保存文件信息
     */
    private void saveFileInfo(FileInfo fileInfo) {
        fileInfoMapper.save(fileInfo);
    }

    /**
     * @Description 更新文件信息
     */
    private void updateFileInfo(FileInfo fileInfo) {
        fileInfoMapper.update(fileInfo);
    }
}
