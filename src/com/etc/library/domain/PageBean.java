package com.etc.library.domain;

import lombok.Getter;

@Getter
public class PageBean {
    //总记录数
    private int recordCount;
    //页数
    private int pageCount;
    //每页显示的条数
    private int num;
    //起始条数
    private int startNum;
    //当前页码
    private int currPage;

    public PageBean(int recordCount, int num, int currPage) {
        this.recordCount = recordCount;
        this.num = num;
        this.currPage = currPage;

        this.pageCount = recordCount % num == 0 ? recordCount / num : (recordCount / num) + 1;
        this.startNum = (currPage - 1) * num;
    }

}
