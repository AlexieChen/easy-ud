package com.sucsoft.easyudsql.repository;

import com.sucsoft.easyudsql.bean.FileInfo;
import com.sucsoft.jt.acjtutil.JtIdCreateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class FileInfoMapperTest {
    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Test
    public void save() {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setDate(new Date());
        fileInfo.setFileName("YangJJTest");
        fileInfo.setFilePath("/usr/nbjy");
        fileInfo.setFileSize(20);
        fileInfo.setFileStatus("suc");
        fileInfo.setMd5("666");
        fileInfo.setId("777");
        fileInfoMapper.save(fileInfo);
    }

    @Test
    public void update() {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileSize(30);
        fileInfo.setId("777");
        fileInfoMapper.update(fileInfo);
    }

    @Test
    public void selectById() {
        System.out.println(fileInfoMapper.selectById("777").toString());

    }

    @Test
    public void selectAll() {
        fileInfoMapper.selectAll();
    }

    @Test
    public void selectByMd5() {
        fileInfoMapper.selectByMd5("666");
    }

    @Test
    public void deleteById() {
        fileInfoMapper.deleteById("777");
    }
}