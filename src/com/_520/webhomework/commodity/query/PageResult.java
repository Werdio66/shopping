package com._520.webhomework.commodity.query;

import java.util.Arrays;
import java.util.List;

public class PageResult {

    private Integer beginPage = 1;        // 起始页
    private Integer courentPage;          // 当前页            用户传递
    private Integer pageSize;             // 每页多少条数据    用户传递
    private Integer prevPage;             // 上一页            计算
    private Integer nextPage;             // 下一页            计算
    private Integer totalPage;            // 最后一页          计算
    private Integer beginIndex = 1;           // 开始
    private Integer endIndex = 2;             // 结束
    private List<Integer> items = Arrays.asList(3,5,10);    // 控制每页多少数据

    private List<?> listDates;      // 查询的结果集
    private Integer totalCount;     // 结果集总数

    public PageResult(Integer courentPage, Integer pageSize, List<?> listDates, Integer totalCount) {
        this.courentPage = courentPage;
        this.pageSize = pageSize;
        this.listDates = listDates;
        this.totalCount = totalCount;

        this.totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        this.prevPage = courentPage - 1 > 1 ? courentPage - 1 : 1;
        this.nextPage = courentPage + 1 < totalPage ? courentPage + 1 : totalPage;
    }


    public List<Integer> getItems() {
        return items;
    }

    public Integer getBeginIndex() {
        return beginIndex;
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public List<?> getListDates() {
        return listDates;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public Integer getBeginPage() {
        return beginPage;
    }

    public Integer getCourentPage() {
        return courentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getPrevPage() {
        return prevPage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }
}
