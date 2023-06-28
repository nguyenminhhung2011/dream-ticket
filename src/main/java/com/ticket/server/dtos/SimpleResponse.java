package com.ticket.server.dtos;

import lombok.Data;

import java.util.List;

enum CODE{
    SUCCESS,
    ERROR
}
@Data
public class SimpleResponse<T> {


    private Integer currentPage;

    private Integer pageSize = 15;

    private Integer totalPages;

    private List<T> responseData;


    public SimpleResponse(Integer currentPage, Integer pageSize,Integer totalPages, List<T> responseData) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.responseData = responseData;
    }
}
