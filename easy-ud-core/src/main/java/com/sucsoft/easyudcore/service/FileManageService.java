package com.sucsoft.easyudcore.service;

import com.sucsoft.easyudcore.bean.FileChunkDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author : "Chenzx"
 * @date : 2019/12/2 09:54
 */
@Service
public class FileManageService {

    public void deleteChunk(String path){
        try {
            File file = new File(path);
            if (file.exists()){
                file.delete();
            }
        }catch (Exception e){

        }

        //TODO 删除
    }

}
