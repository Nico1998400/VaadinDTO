package com.example.spring_boot_controller.dto;

public record BlogResponseDTO(int id, String title, String message, int appuser_id) {
}

/*
public class BlogResponseDTO {

    private int id;
    private String title;
    private String message;
    private int appuser_id;

    public BlogResponseDTO(int id, String title, String message, int appuser_id) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.appuser_id = appuser_id;
    }

    public BlogResponseDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAppuser_id() {
        return appuser_id;
    }

    public void setAppuser_id(int appuser_id) {
        this.appuser_id = appuser_id;
    }
}


 */