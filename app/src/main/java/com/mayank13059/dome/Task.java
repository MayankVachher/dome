package com.mayank13059.dome;

/**
 * Created by Mayank Vachher (2013059) on 09/11/16.
 */

public class Task {

    private String title, deets;
    private Integer status;

    public Task() {
    }

    public Task(String title, String deets, Integer status) {
        this.title = title;
        this.deets = deets;
        this.status = status;
    }

    public String getTitle() {
        return this.title;
    }
    public String getDeets() {
        return this.deets;
    }
    public Integer getStatus() {
        return this.status;
    }


    public void setTitle(String title) {
        this.title = title;
    }
    public void setDeets(String deets) {
        this.deets = deets;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
}
