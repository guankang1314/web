package com.bookstore.bean;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {
    private int pageNo; //当前页
    private int totalPageNo;//总页数
    private int totalRecord;
    public static final int PAGE_SIZE = 4;
    private List<T> list;   //当前页的数据集合

    public int getPageNo() {
        if(pageNo < 1) {
            return 1;
        }
        if(pageNo > getTotalPageNo()) {
            return getTotalPageNo();
        }
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalPageNo() {

        return totalRecord%PAGE_SIZE==0?totalRecord/PAGE_SIZE:totalRecord/PAGE_SIZE+1;
    }

    public void setTotalPageNo(int totalPageNo) {
        this.totalPageNo = totalPageNo;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    /*public static int getPageSize() {
        return PAGE_SIZE;
    }*/

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Page(int pageNo, int totalPageNo, int totalRecord, List<T> list) {
        this.pageNo = pageNo;
        this.totalPageNo = totalPageNo;
        this.totalRecord = totalRecord;
        this.list = list;
    }

    public Page() {
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", totalPageNo=" + totalPageNo +
                ", totalRecord=" + totalRecord +
                ", list=" + list +
                '}';
    }
}
