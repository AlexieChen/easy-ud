package com.sucsoft.easyudsql.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class FileTypeLimitServiceTest {
    @Autowired
    private FileTypeLimitService fileTypeLimitService;

    @Test
    public void fileAllowable() {
        fileTypeLimitService.fileAllowable("D:\\ningbo\\777.bmp");
    }
}