package com.example.spring_boot_controller.views;

import com.example.spring_boot_controller.entities.BlogPost;
import com.example.spring_boot_controller.security.PrincipalUtils;
import com.example.spring_boot_controller.services.BlogService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@Route(value = "/manageposts", layout = AppView.class)
@PermitAll
public class MangePostsView extends VerticalLayout {

    BlogService blogService;
    Grid<BlogPost> grid = new Grid<>(BlogPost.class, false);
    BlogForm blogForm;
    PrincipalUtils principalUtils;

    public MangePostsView(BlogService blogService, PrincipalUtils principalUtils) {
        this.principalUtils = principalUtils;
        this.blogService = blogService;
        blogForm = new BlogForm(blogService, this);
        setAlignItems(Alignment.CENTER);
        add(new H1("Manage posts view"));


        grid.setItems(blogService.findPostByAppUserUsername(PrincipalUtils.getName()));

        grid.addComponentColumn(blogPost -> {

            Button deleteButton = new Button(new Icon(VaadinIcon.CLOSE), evt -> {
                blogService.deleteById(blogPost.getId());
                updateItems();
            });

            deleteButton.addThemeVariants(
                    ButtonVariant.LUMO_PRIMARY,
                    ButtonVariant.LUMO_SMALL,
                    ButtonVariant.LUMO_ERROR
            );

            return deleteButton;
        });

        grid.addColumn(BlogPost::getId).setHeader("Id").setSortable(true);
        grid.addColumn(BlogPost::getTitle).setHeader("Title").setSortable(true);
        grid.addColumn(BlogPost::getMessage).setHeader("Message").setSortable(true);
        //grid.addColumn(blogPost -> blogPost.getAppUser().getUsername()).setHeader("Author").setSortable(true);
        grid.asSingleSelect().addValueChangeListener(evt -> {
            blogForm.setBlogPost(evt.getValue());
        });

        HorizontalLayout main = new HorizontalLayout(grid, blogForm);
        main.setSizeFull();

        add(main);

        Button button = new Button("Add new blogpost", evt -> {
            Dialog dialog = new Dialog();
            BlogForm blogForm = new BlogForm(blogService, this);

            BlogPost blogPost = new BlogPost();
            blogPost.setAppUser(principalUtils.getAppUserFromFromPrincipal());
            blogForm.setBlogPost(blogPost);

            dialog.add(blogForm);
            dialog.open();
        });

        add(button);

    }

    public void updateItems() {
        grid.setItems(blogService.findPostByAppUserUsername(PrincipalUtils.getName()));
    }
}
