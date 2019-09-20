package service;

import bean.FileResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import util.FileUtil;

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
    //存储上传的文件信息，key为文件的md5值，value为文件信息FileResponse
    public Map<String, FileResponse> fileInfoMap = new HashMap();

    public FileResponse upload(MultipartFile file, String uploadDir) throws IOException {
        FileResponse fileResponse = new FileResponse();
        String fileOriginalName =file.getOriginalFilename();
        Integer lastIndexOfDot =fileOriginalName.lastIndexOf(".");
        //文件后缀
        String suffix =fileOriginalName.substring(lastIndexOfDot);
        //文件名除去后缀
        String fileName = fileOriginalName.substring(0, lastIndexOfDot);
        //文件放到随机生成的id，防止重名
        String id = UUID.randomUUID().toString();
        try {

            //String fileType = suffix.substring(1);

            String[] pathList = new String[]{uploadDir, fileName, suffix};
            //文件路径拼接
            String path = StringUtils.join(pathList);
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
            //获得文件的md5值
            String md5 = FileUtil.getFileMD5(dest);
            fileResponse = new FileResponse(fileName, filePath, md5, "Success");
            //保存成功上传的文件信息
            fileInfoMap.put(id, fileResponse);
        } catch (IOException e) {
            e.printStackTrace();
            fileResponse = new FileResponse(fileName, filePath, null, "Fail");
        }
        return fileResponse;
    }

    public List<FileResponse> uploadFiles(List<MultipartFile> files, String uploadDir) throws Exception {
        ArrayList<FileResponse> fileResponses = new ArrayList<FileResponse>();
        for (MultipartFile file : files) {
            fileResponses.add(upload(file, uploadDir));
        }
        return fileResponses;
    }
}
