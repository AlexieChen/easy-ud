package com.sucsoft.easyudsql.repository;

import com.sucsoft.easyudsql.bean.FileInfo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author YangJJ
 * @Date 2019/9/24 10:14
 * @Description
 */
@Component(value = "FileInfoMapper")
public interface FileInfoMapper {
    /**
     * 新增用户
     *
     * @param fileInfo
     * @return
     */
    int save(FileInfo fileInfo);

    /**
     * 更新用户信息
     *
     * @param fileInfo
     * @return
     */
    int update(FileInfo fileInfo);

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    int deleteById(String id);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    FileInfo selectById(String id);

    /**
     * 查询所有用户信息
     *
     * @return
     */
    List<FileInfo> selectAll();

    /**
     * 根据md5查询
     *
     * @param md5
     * @return
     */
    FileInfo selectByMd5(String md5);
}
