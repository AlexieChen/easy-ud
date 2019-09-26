package com.sucsoft.easyudsql.repository;

import com.sucsoft.easyudsql.bean.FileInfo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author YangJJ
 * @date 2019/9/24 10:14
 */
@Component(value = "FileInfoMapper")
public interface FileInfoMapper {
    /**
     * 新增用户
     *
     * @param fileInfo 文件信息类
     */
    void save(FileInfo fileInfo);

    /**
     * 更新用户信息
     *
     * @param fileInfo 文件信息类
     */
    void update(FileInfo fileInfo);

    /**
     * 根据id删除
     *
     * @param id 文件Id
     */
    void deleteById(String id);

    /**
     * 根据id查询
     *
     * @param id 文件Id
     * @return 文件信息
     */
    FileInfo selectById(String id);

    /**
     * 查询所有用户信息
     *
     * @return 文件信息列表
     */
    List<FileInfo> selectAll();

    /**
     * 根据md5查询
     *
     * @param md5 文件MD5值
     * @return 文件信息
     */
    FileInfo selectByMd5(String md5);
}
