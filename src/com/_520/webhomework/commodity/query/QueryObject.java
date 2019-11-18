package com._520.webhomework.commodity.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryObject {

    private Integer curentPage = 1;
    private Integer pageSize = 5;

    private List<Object> paramers = new ArrayList<>();
    private List<String> sqls = new ArrayList<>();

    public String getSql(){
        StringBuilder sql = new StringBuilder("");
        this.cunstomizedQuery();

        if (sqls.size() == 0)
            return "";

        for (String str:sqls
        ) {
            sql.append(" AND ").append(str);
        }
        return sql.toString().replaceFirst("AND","WHERE");
    }

    public List<Object> getParamers(){
        return this.paramers;
    }
    protected void cunstomizedQuery() {
    }

    public void add(Integer integer){
        paramers.add(integer);
    }
    protected void addQuery(String sqls, Object ... paramers){
        this.sqls.add(sqls);
        this.paramers.addAll(Arrays.asList(paramers));
    }





    public void setCurentPage(Integer curentPage) {
        this.curentPage = curentPage;
    }

    public void setPageSize(Integer pageSize) {
        System.out.println("pageSize66 = "+pageSize);
        this.pageSize = pageSize;
    }

    public Integer getCurentPage() {
        return curentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }
}
