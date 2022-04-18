package com.example.spring_boot_controller.views;

import com.example.spring_boot_controller.entities.BlogPost;
import com.example.spring_boot_controller.services.BlogService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;


public class BlogForm extends FormLayout {

    TextField title = new TextField("Title");
    TextArea message = new TextArea("Message");
    Button saveButton = new Button("Save");

    Binder<BlogPost> binder = new BeanValidationBinder<>(BlogPost.class);
    BlogService blogService;
    MangePostsView mangePostsView;

    public BlogForm(BlogService blogService, MangePostsView mangePostsView){
        this.blogService = blogService;
        this.mangePostsView = mangePostsView;
        setVisible(false);
        binder.bindInstanceFields(this);

        saveButton.addClickListener(evt -> onSave());

        add(title, message, saveButton);

    }

    private void onSave() {
        BlogPost blogPost = binder.validate().getBinder().getBean();
        if(blogPost.getId() != 0){
            blogService.updateBlogById(blogPost.getId(), blogPost);
        } else {
            blogService.createBlog(blogPost);
        }
        setBlogPost(null);
        mangePostsView.updateItems();

        this.getParent().ifPresent(component -> {
            if(component instanceof Dialog){
                ((Dialog) component).close();
            }
        });
    }

    public void setBlogPost(BlogPost blogPost){
        if(blogPost != null){
            binder.setBean(blogPost);
            setVisible(true);
        } else {
            setVisible(false);
        }
    }



}
