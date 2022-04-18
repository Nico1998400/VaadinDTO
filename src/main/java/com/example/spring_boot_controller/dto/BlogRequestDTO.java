package com.example.spring_boot_controller.dto;

public record BlogRequestDTO(String title, String message, int appUserId) {
}

/*
public class BlogRequestDTO {

    private String title;
    private String message;
    private int appUserId;

    public BlogRequestDTO(String title, String message, int appUserId) {
        this.title = title;
        this.message = message;
        this.appUserId = appUserId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }
}



 */