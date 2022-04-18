package com.example.spring_boot_controller.services;

import com.example.spring_boot_controller.entities.BlogPost;
import com.example.spring_boot_controller.repositories.BlogPostRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    //@Autowired
    //BlogPostRepository blogPostRepository;

    BlogPostRepository blogPostRepository;

    public BlogService(BlogPostRepository blogPostRepository){

        this.blogPostRepository = blogPostRepository;
    }

    public List<BlogPost> findAll() {

        return blogPostRepository.findAll();
    }

    public BlogPost findBlogById(int id) {

        return blogPostRepository.findById(id).orElseThrow();
    }

    public void deleteById(int id) {

        blogPostRepository.deleteById(id);
    }

    public BlogPost createBlog(BlogPost blogPost) {

        return blogPostRepository.save(blogPost);
    }

    public BlogPost updateBlogById(int id, BlogPost changedBlogPost) {

        BlogPost blogPost = blogPostRepository.findById(id).orElseThrow();

        if(changedBlogPost.getTitle() != null)
            blogPost.setTitle(changedBlogPost.getTitle());
        if(changedBlogPost.getMessage() != null)
            blogPost.setMessage(changedBlogPost.getMessage());

        return blogPostRepository.save(blogPost);

        //BeanUtils.copyProperties(changedBlogPost, existingBlogPost, "id");
    }

    public List<BlogPost> findPostByAppUserUsername(String name) {
        return blogPostRepository.findByAppUser_Username(name);
    }
}
