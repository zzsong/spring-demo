package com.zss.web.export.xlsx.support;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.zss.web.export.xlsx.BookDO;
import com.zss.web.export.xlsx.MemberDO;

public class BookReadListener extends AnalysisEventListener<BookDO> {

    @Override
    public void invoke(BookDO data, AnalysisContext context) {
        System.out.println(JSON.toJSONString(data));
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("book read finish ");
    }
}
