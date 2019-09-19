package service;

import bean.FileForm;
import bean.FileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: "Chenzx"
 * @Date: 2019/9/19 15:34
 * @Description:
 */
@Service
public class FileUploadService
{
    @Autowired
    private FileBasicUploadService fileBasicUploadService;

}
