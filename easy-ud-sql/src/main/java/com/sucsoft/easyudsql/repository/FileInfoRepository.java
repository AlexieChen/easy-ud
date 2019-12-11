package com.sucsoft.easyudsql.repository;

import com.mongodb.WriteResult;
import com.sucsoft.easyudsql.bean.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : "Chenzx"
 * @date : 2019/12/2 15:26
 */
@Component
public class FileInfoRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void save(FileInfo fileInfo) {
        mongoTemplate.save(fileInfo, "fileInfo");
    }

    public void update(FileInfo fileInfo) {
        Query query = Query.query(Criteria.where("id").is(fileInfo.getId()));
        Update update = new Update();
        mongoTemplate.upsert(query, update, FileInfo.class, "fileInfo");
    }

    public FileInfo findById(String id){
        return mongoTemplate.findById(id,FileInfo.class);
    }

    public List<FileInfo> findAll(){
        return mongoTemplate.findAll(FileInfo.class);
    }

    public void findAndDelete(String id){
        Query query = Query.query(Criteria.where("id").is(id));
        mongoTemplate.findAndRemove(query,FileInfo.class,"fileInfo");
    }
}
