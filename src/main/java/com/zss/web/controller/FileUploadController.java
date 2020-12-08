package com.zss.web.controller;

import com.zss.web.domain.MultiFileParam;
import com.zss.web.domain.UploadParam;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("file")
public class FileUploadController {

    @Value("${file.upload.path}")
    private String uploadPath;

    @RequestMapping(value = "upload1", produces = "text/html;charset=utf-8")
    public String  fileUpload1(@RequestParam("file") MultipartFile file, int tradeDate) throws IOException {
        String path=uploadPath+ File.separator + "upload1" + File.separator +file.getOriginalFilename();
        System.out.println("fileName："+file.getOriginalFilename()+"....>>>"+tradeDate);

        File destination = new File(path);
        if (!destination.getParentFile().exists()){
            FileUtils.forceMkdirParent(destination);
        }
        file.transferTo(destination);
        return "success:" + path;
    }

    /**
     * 把文件放入到body使用二进制流进行上传
     * @param filename
     * @param request
     * @return
     * @throws IOException
     * @throws ServletException
     */
    @RequestMapping("upload2")
    public String  fileUpload2(String filename, HttpServletRequest request) throws IOException, ServletException {
        File dir = new File(uploadPath);
        if (!dir.exists()){
            FileUtils.forceMkdir(dir);
        }
        String file = uploadPath + File.separator + "upload2" ;
        String suffixFile = "."+FilenameUtils.getExtension(filename);
        File tempFile = File.createTempFile(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE) +"_",suffixFile,dir);
        FileUtils.copyInputStreamToFile(request.getInputStream(), tempFile);
        return "success: "+ tempFile.getAbsolutePath();
    }

    @RequestMapping("upload3")
    public String  fileUpload3(UploadParam param) throws IOException {
        String file = uploadPath + File.separator + "upload3" + File.separator + param.getFile().getOriginalFilename();
        File destination = new File(file);
        if (!destination.getParentFile().exists()){
            FileUtils.forceMkdirParent(destination);
        }
        param.getFile().transferTo(destination);
        return "success: " + file;
    }

    @RequestMapping("upload4")
    public String  fileUpload4(MultiFileParam param) throws IOException {
        String file = uploadPath + File.separator + "upload4" ;
        File destination = new File(file);
        if (!destination.exists()){
            FileUtils.forceMkdirParent(destination);
        }

        param.getFile().forEach(f->{
            File dest = new File(file + File.separator + f.getOriginalFilename());
            try {
                f.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return "success: " + file;
    }

}
