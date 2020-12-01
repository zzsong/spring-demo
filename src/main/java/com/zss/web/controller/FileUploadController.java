package com.zss.web.controller;

import com.zss.web.domain.UploadParam;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("file")
public class FileUploadController {

    @Value("${file.upload.path}")
    private String uploadPath;

    @RequestMapping("upload1")
    public String  fileUpload1(@RequestParam("file") CommonsMultipartFile file, int tradeDate) throws IOException {
        long  startTime=System.currentTimeMillis();

        System.out.println("fileName："+file.getOriginalFilename());

        String path=uploadPath+ File.separator + "upload1" + File.separator +file.getOriginalFilename();

        File newFile=new File(path);
        file.transferTo(newFile);
        long  endTime=System.currentTimeMillis();
        System.out.println("方法二的运行时间："+String.valueOf(endTime-startTime)+"ms");
        return "success";
    }

    @RequestMapping("upload2")
    public String  fileUpload2(String filename, int tradeDate, HttpServletRequest request) throws IOException {
        long  startTime=System.currentTimeMillis();
        System.out.println(filename+" \t ===>>>"+tradeDate);
        String file = uploadPath + File.separator + "upload2" + File.separator + filename;
        File destination = new File(file);
        if (!destination.getParentFile().exists()){
            FileUtils.forceMkdirParent(destination);
        }
        FileUtils.copyInputStreamToFile(request.getInputStream(), destination);

        System.out.println(""+String.valueOf(startTime)+"ms");
        return "success";
    }

    @RequestMapping("upload3")
    public String  fileUpload3(UploadParam param) throws IOException {
        long  startTime=System.currentTimeMillis();

        String file = uploadPath + File.separator + "upload3" + File.separator + param.getFile().getOriginalFilename();
        File destination = new File(file);
        if (!destination.getParentFile().exists()){
            FileUtils.forceMkdirParent(destination);
        }
        param.getFile().transferTo(destination);

        System.out.println(""+String.valueOf(startTime)+"ms");
        return "success";
    }

}
