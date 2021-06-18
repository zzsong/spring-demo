package com.zss.web.csv;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RestController
@RequestMapping("csv")
public class CsvController {

    /*
    Header Field Definitions:
    https://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html
     */
    @RequestMapping("export1")
    public void exportCsv(HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/csv");
//        response.seth

    }



    private List<OrderDO> queryList(){
        List<OrderDO> list = new ArrayList<>();
        IntStream.range(1,10).forEach(i->{
            OrderDO order = new OrderDO();
            order.setId(i);
            order.setOrderNo("NO00"+i);
            order.setOccurDate(LocalDate.now().plus(i, ChronoUnit.DAYS));
            order.setOrderTime(LocalDateTime.now().plusHours(i));
            order.setAmount(BigDecimal.valueOf(25.56).add(BigDecimal.valueOf(i)));
            order.setNameList(Arrays.asList("张三","李四"));
            list.add(order);
        });
        return list;
    }
}
