package com.zss.result;

import java.io.Serializable;
import java.util.List;


public class DataPage<T> implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    //-- 分页参数 --//
    private int pageNo = 1;
    private int pageSize = 20;
    //-- 返回结果 --//
    private List<T> dataList = null;
    private long totalCount = -1;

    //-- 构造函数 --//
    public DataPage() {
    }

    public DataPage(int pageSize) {
        this.pageSize = pageSize;
    }
    
    public DataPage(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    //-- 分页参数访问函数 --//
    /**
     * 获得当前页的页号,序号从1开始,默认为1.
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
     */
    public void setPageNo(final int pageNo) {
        this.pageNo = pageNo;

        if (pageNo < 1) {
            this.pageNo = 1;
        }
    }

    /**
     * 返回Page对象自身的setPageNo函数,可用于连续设置。
     */
    public DataPage<T> pageNo(final int thePageNo) {
        setPageNo(thePageNo);
        return this;
    }

    /**
     * 获得每页的记录数量, 默认为-1.
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置每页的记录数量.
     */
    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 返回Page对象自身的setPageSize函数,可用于连续设置。
     */
    public DataPage<T> pageSize(final int thePageSize) {
        setPageSize(thePageSize);
        return this;
    }

    /**
     * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始.
     */
    public int getFirst() {
        return ((pageNo - 1) * pageSize);
    }


    //-- 访问查询结果函数 --//

    /**
     * 获得页内的记录列表.
     */
    public List<T> getDataList() {
        return dataList;
    }

    /**
     * 设置页内的记录列表.
     */
    public void setDataList(final List<T> result) {
        this.dataList = result;
    }

    /**
     * 获得总记录数, 默认值为-1.
     */
    public long getTotalCount() {
        return totalCount;
    }

    /**
     * 设置总记录数.
     */
    public void setTotalCount(final long totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 根据pageSize与totalCount计算总页数, 默认值为-1.
     */
    public long getTotalPages() {
        if (totalCount < 0) {
            return -1;
        }

        long count = totalCount / pageSize;
        if (totalCount % pageSize > 0) {
            count++;
        }
        return count;
    }

    /**
     * 是否还有下一页.
     */
    public boolean isHasNext() {
        return (pageNo + 1 <= getTotalPages());
    }

    /**
     * 取得下页的页号, 序号从1开始.
     * 当前页为尾页时仍返回尾页序号.
     */
    public int getNextPage() {
        if (isHasNext()) {
            return pageNo + 1;
        } else {
            return pageNo;
        }
    }
	public int getEndIndex() {
		return pageNo * pageSize;
	}

	public int getStartIndex() {
		return this.getFirst();
	}
    /**
     * 是否还有上一页.
     */
    public boolean isHasPrev() {
        return (pageNo - 1 >= 1);
    }

    /**
     * 取得上页的页号, 序号从1开始.
     * 当前页为首页时返回首页序号.
     */
    public int getPrevPage() {
        if (isHasPrev()) {
            return pageNo - 1;
        } else {
            return pageNo;
        }
    }
}
