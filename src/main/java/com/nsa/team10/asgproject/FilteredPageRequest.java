package com.nsa.team10.asgproject;

public class FilteredPageRequest
{
    private long page;
    private byte pageSize;
    private String orderBy;
    private boolean orderByAscending;
    private String searchTerm;

    public FilteredPageRequest(long page, byte pageSize, String orderBy, boolean orderByAscending, String search)
    {
        if (page < 1)
            throw new IllegalArgumentException("Page cannot be less than 1");
        if (pageSize < 1)
            throw new IllegalArgumentException("Page cannot be less than 1");

        this.page = page;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
        this.orderByAscending = orderByAscending;
        this.searchTerm = search;
    }

    public long getPage()
    {
        return page;
    }

    public byte getPageSize()
    {
        return pageSize;
    }

    public String getOrderBy()
    {
        return orderBy;
    }

    public long getOffset()
    {
        return (page - 1) * pageSize;
    }

    public String getOrderByAscending()
    {
        return orderByAscending ? " ASC" : " DESC";
    }

    public String getSearchTermSql()
    {
        return searchTerm.trim() + "%";
    }
}

