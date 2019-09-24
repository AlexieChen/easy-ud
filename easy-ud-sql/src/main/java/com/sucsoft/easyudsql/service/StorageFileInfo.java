package com.sucsoft.easyudsql.service;

import com.sucsoft.easyudsql.bean.FileInfo;
import com.sucsoft.easyudsql.bean.Fileform;
import com.sucsoft.easyudsql.repository.FileInfoMapper;
import com.sucsoft.jt.acjtutil.JtIdCreateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    private List<FileInfo> queryAllFileInfo() {
        return fileInfoMapper.selectAll();
    }

    private FileInfo queryFileInfoById(String id) {
        return fileInfoMapper.selectById(id);
    }

    private FileInfo queryFileInfoByMd5(String md5) {
        return fileInfoMapper.selectByMd5(md5);
    }

    private void saveFileInfo(FileInfo fileInfo) {
        fileInfoMapper.save(fileInfo);
    }

    private void updateFileInfo(FileInfo fileInfo) {
        fileInfoMapper.update(fileInfo);
    }
}
