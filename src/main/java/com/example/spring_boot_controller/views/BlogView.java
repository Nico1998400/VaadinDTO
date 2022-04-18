package com.example.spring_boot_controller.views;

import com.example.spring_boot_controller.services.BlogService;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "/", layout = AppView.class)
@AnonymousAllowed
public class BlogView extends VerticalLayout {

    BlogService blogService;

    public BlogView(BlogService blogService){
        this.blogService = blogService;

        setAlignItems(Alignment.CENTER);

        blogService.findAll().forEach(blogPost -> {

            H2 blogTitle = new H2(blogPost.getTitle());
            Paragraph blogMessage = new Paragraph(blogPost.getMessage());

            Paragraph writtenBy = new Paragraph("Written by: ");
            Span author = new Span(blogPost.getAppUser().getUsername());
            author.getStyle().set("font-weight", "bold");
            writtenBy.add(author);

            add(blogTitle, blogMessage, writtenBy, new Hr());

        });


    }

}
