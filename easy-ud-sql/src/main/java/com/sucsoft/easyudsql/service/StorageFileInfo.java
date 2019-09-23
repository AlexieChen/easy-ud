package com.sucsoft.easyudsql.service;

import bean.FileInfo;
import bean.Fileform;
import com.sucsoft.jt.acjtutil.JtIdCreateUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import util.SqlSessionUtil;

import java.io.*;
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
    private MongoTemplate mongoTemplate;

    private FileInfo saveFileInfo(Fileform form, String filePath, String fileStatus) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setDate(new Date());
        fileInfo.setFileName(form.getFileName());
        fileInfo.setFilePath(filePath);
        fileInfo.setFileSize(form.getFileSize());
        fileInfo.setId(JtIdCreateUtil.Companion.generateUUID());
        fileInfo.setMd5(form.getMd5());
        fileInfo.setFileStatus(fileStatus);
        return fileInfo;
    }

    private SqlSession initSqlSession() {
        //获取mybatis-config.xml文件路径
        String resource = "mybatis-config.xml";
        //创建流
        Reader reader = null;
        try {
            //读取mybatis-config.xml文件到reader对象中
            reader = Resources.getResourceAsReader(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //初始化mybatis,创建SqlSessionFactory类的实例
        SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
        //创建session实例
        return sqlMapper.openSession();
    }

    private List<FileInfo> queryAllFileInfo() {
        //创建SqlSession实例
        SqlSession session = initSqlSession();
        //传入参数查询，返回结果
        List<FileInfo> fileInfoList = session.selectList("findAll");
        //关闭session
        session.close();
        return fileInfoList;
    }

    private FileInfo queryFileInfoById(String id) {
        //创建SqlSession实例
        SqlSession session = initSqlSession();
        //传入参数查询，返回结果
        FileInfo fileInfo = session.selectOne("findById", id);
        //关闭session
        session.close();
        return fileInfo;
    }

    private FileInfo queryFileInfoByMd5(String md5) {
        //创建SqlSession实例
        SqlSession session = initSqlSession();
        //传入参数查询，返回结果
        FileInfo fileInfo = session.selectOne("findByMd5", md5);
        //关闭session
        session.close();
        return fileInfo;
    }

    @Value("${fileInfoFolder:D\\fileInfo\\}")
    private String txtPath = "";

    public void saveFileInfoInText(Fileform form, String filePath, String fileStatus) {
        FileInfo fileInfo = saveFileInfo(form, filePath, fileStatus);
        File file = new File(txtPath + "fileInfo.txt");
        try {
            Writer out = new FileWriter(file);
            out.write(fileInfo.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveFileInfoInMongo(Fileform form, String filePath, String fileStatus) {
        mongoTemplate.save(saveFileInfo(form, filePath, fileStatus), "fileInfo");
    }
}
