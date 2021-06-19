package com.zss.web.export.xlsx;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.zss.web.export.xlsx.support.BookReadListener;
import com.zss.web.export.xlsx.support.LocalDateNumberConverter;
import com.zss.web.export.xlsx.support.LocalDateStringConverter;
import com.zss.web.export.xlsx.support.MemberReadListener;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @RequestMapping("upload1")
    public String upload1(MultipartFile file) throws IOException {
        System.out.println("filename:\t"+file.getOriginalFilename());
        EasyExcel.read(file.getInputStream(),MemberDO.class, new MemberReadListener())
                .excelType(ExcelTypeEnum.XLSX)
                .sheet().doRead();
        return "success";
    }

    @RequestMapping("upload2")
    public String upload2(MultipartFile file) throws IOException {
        System.out.println("filename:\t"+file.getOriginalFilename());
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(file.getInputStream()).build();
            ReadSheet readSheet1 =
                    EasyExcel.readSheet(0).head(MemberDO.class).registerReadListener(new MemberReadListener()).build();
            ReadSheet readSheet2 =
                    EasyExcel.readSheet(1).head(BookDO.class).registerReadListener(new BookReadListener()).build();
            // 这里注意 一定要把sheet1 sheet2 一起传进去，不然有个问题就是03版的excel 会读取多次，浪费性能
            excelReader.read(readSheet1, readSheet2);
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }
        return "success";
    }


    /**
     * 设置生成excel样式 去除默认表头样式及设置内容居中，如有必要可重载该方法给定参数配置不同样式
     *
     * @return HorizontalCellStyleStrategy
     */
    public static HorizontalCellStyleStrategy getStyleStrategy() {
        //内容样式策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        //垂直居中,水平居中
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
        contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
        contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
        contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
        //设置 自动换行
        contentWriteCellStyle.setWrapped(true);
        // 字体策略
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short) 12);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        //头策略使用默认
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        return new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
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
