package com.example.spring_boot_controller.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    @NotBlank
    private String message;

    @ManyToOne
    @JoinColumn(name = "appuser_id")
    private AppUser appUser;

    public BlogPost(String title, String message, AppUser appUser) {
        this.title = title;
        this.message = message;
        this.appUser = appUser;
    }

    public BlogPost() {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
