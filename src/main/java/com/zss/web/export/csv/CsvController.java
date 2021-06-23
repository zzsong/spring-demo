package com.zss.web.export.csv;

import com.alibaba.fastjson.JSON;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@RestController
@RequestMapping("csv")
public class CsvController {

    /*
    Header Field Definitions:
    https://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html
    https://blog.csdn.net/xiaoyu19910321/article/details/79279364

    All possible values of HTTP Content-type header:
    Type	Values
    Application
    application/EDI-X12
    application/EDIFACT
    application/javascript
    application/octet-stream
    application/ogg
    application/pdf
    application/xhtml+xml
    application/x-shockwave-flash
    application/json
    application/ld+json
    application/xml
    application/zip
    application/x-www-form-urlencoded
    Audio
    audio/mpeg
    audio/x-ms-wma
    audio/vnd.rn-realaudio
    audio/x-wav
    Image
    image/gif
    image/jpeg
    image/png
    image/tiff
    image/vnd.microsoft.icon
    image/x-icon
    image/vnd.djvu
    image/svg+xml
    Multipart
    multipart/mixed
    multipart/alternative
    multipart/related (using by MHTML (HTML mail).)
    multipart/form-data
    Text
    text/css
    text/csv
    text/html
    text/javascript (obsolete)
    text/plain
    text/xml
    Video
    video/mpeg
    video/mp4
    video/quicktime
    video/x-ms-wmv
    video/x-msvideo
    video/x-flv
    video/webm
    VND
    application/vnd.oasis.opendocument.text
    application/vnd.oasis.opendocument.spreadsheet
    application/vnd.oasis.opendocument.presentation
    application/vnd.oasis.opendocument.graphics
    application/vnd.ms-excel    //2003 Excel
    application/vnd.openxmlformats-officedocument.spreadsheetml.sheet   //2010 Excel
    application/vnd.ms-powerpoint       //.ppt
    application/vnd.openxmlformats-officedocument.presentationml.presentation   //.pptx
    application/msword      //.doc
    application/vnd.openxmlformats-officedocument.wordprocessingml.document     //.docx
    application/vnd.mozilla.xul+xml
     */
    @RequestMapping("export1")
    public void exportCsv(HttpServletResponse response) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/csv; charset=utf-8");
        //Content-Disposition响应标头是指示内容是否预期在浏览器中内联显示的标题，即，作为网页或作为网页的一部分或作为附件下载并且本地保存。
        //
        /*
        作为主体的响应标题
        Content-Disposition: attachment; filename="filename.jpg"
        attachment（表示它应该下载;大多数浏览器呈现“另存为”对话框，预先填入filename如果存在参数的值

        作为多部分主体的 header
        Content-Disposition: form-data; name="fieldName"; filename="filename.jpg"
        name后面跟着一个字符串，其中包含该字段的内容引用的 HTML 字段的名称。
        filename后面是一个包含传输文件的原始名称的字符串。

        Content-Encoding实体头用于压缩媒体类型。如果存在，它的值表示哪些编码应用于实体主体。它让客户知道，如何解码以获取Content-Type标题引用的媒体类型。
        Content-Encoding: gzip, identity

        gzip    一种使用 Lempel-Ziv 编码（ LZ77 ）和32位 CRC 的格式。这最初是 UNIX gzip 程序的格式。
        x-gzip  为了兼容性的目的，HTTP / 1.1 标准还建议支持该内容编码的服务器应该将其识别为别名。
        compress    使用 Lempel-Ziv-Welch（ LZW ）算法的格式。值名取自实施此算法的 UNIX 压缩程序。
        与大多数 UNIX 发行版已经消失的压缩程序一样，目前几乎没有浏览器使用这种内容编码，部分原因是由于专利问题（已在2003年过期）。
        deflate 使用 deflate 压缩算法（在 RFC 1951中定义）使用 zlib 结构（在 RFC 1950中定义）。
        identity    指示身份功能（即不压缩，也不修改）。除非明确指定，否则此标记始终被视为可接受。
        br  使用 Brotli 算法的格式。
         */
        // 对真实文件名进行编码
        //.replaceAll("\\+", "%20");  处理文件名中包含空格
        String percentEncodedFileName = URLEncoder.encode("中文csv下载 文件.csv", "utf-8")
                .replaceAll("\\+", "%20");
        //filename 的 value 不进行编码
        //优先使用filename*
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename="+percentEncodedFileName+";filename*="+percentEncodedFileName);


        CustomMappingStrategy columnStrategy = new CustomMappingStrategy();
        columnStrategy.setType(OrderDO.class);

        //create a csv writer
        StatefulBeanToCsv<OrderDO> writer = new StatefulBeanToCsvBuilder<OrderDO>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withMappingStrategy(columnStrategy)
                .withOrderedResults(false)
                .build();

        //write all users to csv file
        writer.write(queryList());
    }

    @RequestMapping("export2")
    public void export2(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/csv; charset=utf-8");
        String percentEncodedFileName = URLEncoder.encode("中文csv 下载 文件 .csv", "utf-8").replaceAll("\\+", "%20");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\"export2.csv\";filename*="+percentEncodedFileName);


        String[] titles = {"名称","颜色","标签"};
//        String[] items1 = {"book", "read,", "pencil"};
//        String[] items2 = {"pen", "yellow", "lamp"};
//        String[] items3 = {"ball", "黑色", "spectacles"};

        List<String[]> contentList = new ArrayList<>();
        IntStream.range(1,10).forEach(i->{
            String[] item = {"book书"+i, "红色,red", "pencil"+i};
            contentList.add(item);
        });

        CSVWriter writer = new CSVWriter(response.getWriter());

        writer.writeNext(titles);

        writer.writeAll(contentList);
    }

    @RequestMapping("upload")
    public void upload(MultipartFile file) throws IOException {

        Reader reader2 = new InputStreamReader(file.getInputStream(), "UTF-8");
        CsvToBean<OrderDO> csvToBean = new CsvToBeanBuilder<OrderDO>(reader2)
                .withType(OrderDO.class)
                .build();
        csvToBean.parse().forEach(orderDO -> System.out.println(JSON.toJSONString(orderDO)));
    }



    private List<OrderDO> queryList(){
        List<OrderDO> list = new ArrayList<>();
        IntStream.range(1,10).forEach(i->{
            OrderDO order = new OrderDO();
            order.setId(i);
            order.setOrderNo("NO000"+i);
            order.setOccurDate(LocalDate.now().plus(i, ChronoUnit.DAYS));
            order.setOrderTime(LocalDateTime.now().plusHours(i));
            order.setAmount(BigDecimal.valueOf(25.56).add(BigDecimal.valueOf(i)));
            order.setNameList(Arrays.asList("张三","李四"));
            list.add(order);
        });
        return list;
    }
}
