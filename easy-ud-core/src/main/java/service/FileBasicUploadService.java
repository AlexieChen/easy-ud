package service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.*;

/**
 * @Auther: "Chenzx"
 * @Date: 2019/9/19 09:32
 * @Description:
 */
@Service
public class FileBasicUploadService {
    @Value("/usr/upload/")
    private String filePath;

    public Map<String, String> upload(MultipartFile file,String uploadDir) throws IOException {
        Map fileInfo = new HashMap<String, String>();
        try {
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."));
            //String fileType = suffix.substring(1);
            String id = UUID.randomUUID().toString();
            //String[] pathList = new String[]{filePath, fileType, id, fileName, suffix};
            String[] pathList = new String[]{uploadDir,fileName,suffix};
            String path = StringUtils.join(pathList, File.separator);
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                // 新建目录
                dest.getParentFile().mkdirs();
            }
            //int j = 1;
/*            while (dest.exists()) {
                fileName = fileOtherName + j + suffix
                dest = File("$filePath$type/$fileName")
                j++
            }*/
            file.transferTo(dest);
            fileInfo.put("fileName", fileName);
            fileInfo.put("uploadPath",dest);
            fileInfo.put("id",id);
        }
        catch (Exception e){
            e.printStackTrace();
            fileInfo.put("Error",e.getMessage());
        }
        finally {
            return fileInfo;
        }
    }
}
