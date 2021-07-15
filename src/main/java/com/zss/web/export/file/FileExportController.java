package com.zss.web.export.file;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@RestController
public class FileExportController {

    @RequestMapping("export/file")
    public void exportFile(HttpServletResponse response) throws IOException {
        String fileName = "F:\\del\\20210401-140124.csv";
        String name = FilenameUtils.getName(fileName);
        File file = new File(fileName);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/csv; charset=utf-8");
        String percentEncodedFileName = URLEncoder.encode(name, "utf-8").replaceAll("\\+", "%20");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\"export2.csv\";filename*="+percentEncodedFileName);

        OutputStream out = response.getOutputStream();
        try (FileInputStream in = new FileInputStream(file)){
            IOUtils.copy(in, out);
        } catch (Exception ex){

        }

    }
}
