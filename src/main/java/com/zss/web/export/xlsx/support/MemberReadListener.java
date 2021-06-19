package com.zss.web.export.xlsx.support;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.fastjson.JSON;
import com.zss.web.export.xlsx.MemberDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemberReadListener extends AnalysisEventListener<MemberDO> {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void invoke(MemberDO data, AnalysisContext context) {
        System.out.println(JSON.toJSONString(data));
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("member read finish ");
    }

    /**
     * 监听器实现这个方法就可以在读取数据的时候获取到异常信息
     */
    @Override
    public void onException(Exception exception, AnalysisContext context) {
        log.error("解析失败，但是继续解析下一行:{}", exception.getMessage());
        // 如果是某一个单元格的转换异常 能获取到具体行号
        // 如果要获取头的信息 配合invokeHeadMap使用
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException)exception;
            log.error("第{}行，第{}列解析异常", excelDataConvertException.getRowIndex(),
                    excelDataConvertException.getColumnIndex());
        }
    }
}
