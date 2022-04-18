package com.example.spring_boot_controller.controllers;

import com.example.spring_boot_controller.dto.BlogRequestDTO;
import com.example.spring_boot_controller.dto.BlogResponseDTO;
import com.example.spring_boot_controller.dto.DtoConverter;
import com.example.spring_boot_controller.entities.BlogPost;
import com.example.spring_boot_controller.repositories.BlogPostRepository;
import com.example.spring_boot_controller.services.BlogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    BlogService blogService;

    @Autowired
    DtoConverter dtoConverter;

    @GetMapping
    public String getBlogPostList(
            @RequestParam(required = false) String username,
            Model model
    ) {
        List<BlogPost> blogPostList = blogService.findAll();
        model.addAttribute("blogPostList", blogPostList);
        return "blog";
    }

    @GetMapping("/{id}")
    public BlogResponseDTO getBlogPostById(@PathVariable("id") int id){
        BlogPost blogPost = blogService.findBlogById(id);
        return dtoConverter.entityToResponseDto(blogPost);
    }

    @GetMapping("/addblog")
    public String addBlogForm(){
        return "addBlogForm";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostById(@PathVariable("id") int id){
        blogService.deleteById(id);
        return ResponseEntity.status(303).header("Location", "/blog").build();
    }

    @PostMapping
    public String createBlogPost(@ModelAttribute BlogPost blogPost){
        blogService.createBlog(blogPost);
        return "redirect:/blog";
    }

    /*
    @PostMapping
    public BlogResponseDTO createNewBlogpost(@RequestBody BlogRequestDTO blogRequestDTO){
        BlogPost blogPostIn = dtoConverter.RequestDtoToEntity(blogRequestDTO);

        BlogPost blogPostOut = blogService.createBlog(blogPostIn);
        return dtoConverter.entityToResponseDto(blogPostOut);
    }

     */

    @PutMapping("/{id}")
    public BlogResponseDTO updateBlogPostById(
            @PathVariable("id") int id,
            @RequestBody BlogRequestDTO changedBlogPostDTO){

        BlogPost changedBlogPost = dtoConverter.RequestDtoToEntity(changedBlogPostDTO);

        BlogPost blogPostOut = blogService.updateBlogById(id, changedBlogPost);
        return dtoConverter.entityToResponseDto(blogPostOut);

    }




}
