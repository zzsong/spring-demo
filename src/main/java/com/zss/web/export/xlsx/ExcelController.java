package com.zss.web.export.xlsx;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.zss.web.export.xlsx.support.LocalDateNumberConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@RestController
@RequestMapping("excel")
public class ExcelController {

    @RequestMapping("export1")
    public void export1(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("excel测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        EasyExcel.write(response.getOutputStream(), BookDO.class)
//                .registerConverter(new LocalDateNumberConverter()) //也可以在do属性中定义
                .sheet("模板").doWrite(queryList());
    }

    @RequestMapping("export2")
    public void export2(HttpServletResponse response) throws IOException {

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("多个sheet测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();

        WriteSheet oneSheet = EasyExcel.writerSheet(1,"第一个").head(BookDO.class).build();
        excelWriter.write(queryList(),oneSheet);
        WriteSheet twoSheet = EasyExcel.writerSheet(2,"第2个").head(BookDO.class).build();
        excelWriter.write(queryList(),twoSheet);

        excelWriter.finish();
    }


    private List<BookDO> queryList(){
        List<BookDO> list = new ArrayList<>();
        IntStream.range(1,10).forEach(i->{
            BookDO book = new BookDO();
            book.setName("张三"+i);
            book.setAmount(BigDecimal.valueOf(100.12345));
            book.setDate(LocalDate.now().plusDays(i));
            book.setIssueDate(LocalDate.now().plusDays(i));
            book.setPrice(20.987654);
            list.add(book);
        });
        return list;
    }
}
