package com.mqm.community.dto;

import java.util.ArrayList;
import java.util.List;

public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirst;
    private boolean showEnd;
    private boolean showNext;
    private Integer page;
    private List<Integer> pages= new ArrayList<>();
    private Integer totalPage;

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }

    public boolean isShowPrevious() {
        return showPrevious;
    }

    public void setShowPrevious(boolean showPrevious) {
        this.showPrevious = showPrevious;
    }

    public boolean isShowFirst() {
        return showFirst;
    }

    public void setShowFirst(boolean showFirst) {
        this.showFirst = showFirst;
    }

    public boolean isShowEnd() {
        return showEnd;
    }

    public void setShowEnd(boolean showEnd) {
        this.showEnd = showEnd;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public void  setPagination(Integer totalPage, Integer page) {
        this.totalPage = totalPage;
        this.page = page;
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0,page-i);
            }

            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        //show previous page?
        if(page == 1){
            showPrevious = false;
        }else {
            showPrevious = true;
        }
        //show next page?
        if (page == totalPage){
            showNext = false;
        }else{
            showNext = true;
        }
        //show first page?
        if(pages.contains(1)){
            showFirst = false;
        }else {
            showFirst = true;
        }
        //show last page
        if(pages.contains(totalPage)){
            showEnd = false;
        }else {
            showEnd = true;
        }


    }
}
