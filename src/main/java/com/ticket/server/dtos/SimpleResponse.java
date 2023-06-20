package com.ticket.server.dtos;

import java.util.List;

enum CODE{
    SUCCESS,
    ERROR
}
public class SimpleResponse<T> {
    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
    public List<T> getResponseData() {
        return responseData;
    }
    public void setResponseData(List<T> responseData) {
        this.responseData = responseData;
    }

    private Integer currentPage;

    private Integer pageSize = 15;

    private Integer totalPages;



    public SimpleResponse(Integer currentPage, Integer pageSize,Integer totalPages, List<T> responseData) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.responseData = responseData;
    }
    private List<T> responseData;
}
