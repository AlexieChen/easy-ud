package service;

import bean.FileInfo;
import bean.Fileform;
import com.sucsoft.jt.acjtdview.service.dcrun.DcSqlExcute;
import com.sucsoft.jt.acjtutil.JtIdCreateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;

@Service
public class storageFileInfo {

    @Autowired
    private DcSqlExcute dcSqlExcute;
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

    public void saveFileInfoInMysql(Fileform form, String filePath, String fileStatus) {
        dcSqlExcute.save(saveFileInfo(form, filePath, fileStatus));
    }

    public void saveFileInfoInMongo(Fileform form, String filePath, String fileStatus) {
        mongoTemplate.save(saveFileInfo(form, filePath, fileStatus), "fileInfo");
    }
}
