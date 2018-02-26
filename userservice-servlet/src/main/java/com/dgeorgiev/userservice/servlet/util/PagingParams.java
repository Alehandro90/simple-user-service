package com.dgeorgiev.userservice.servlet.util;

public class PagingParams
{
    public final int offset;
    public final int limit;

    public PagingParams(int offset, int limit)
    {
        this.offset = offset;
        this.limit = limit;
    }
}
