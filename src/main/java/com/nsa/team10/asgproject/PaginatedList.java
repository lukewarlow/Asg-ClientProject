package com.nsa.team10.asgproject;

import lombok.Getter;

import java.util.List;

public class PaginatedList<T>
{
    private List<T> list;
    private long totalCount;
    private byte pageSize;
    private long page;

    public PaginatedList(List<T> list, long totalCount, FilteredPageRequest page)
    {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = page.getPageSize();
        this.page = page.getPage();
    }

    public List<T> getList()
    {
        return list;
    }

    public byte getPageSize()
    {
        return pageSize;
    }

    public long getPage()
    {
        return page;
    }

    public long getNoOfPages()
    {
        return (long) Math.ceil((float) totalCount / pageSize);
    }
}

