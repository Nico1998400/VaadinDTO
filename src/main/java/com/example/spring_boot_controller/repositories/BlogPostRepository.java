package com.example.spring_boot_controller.repositories;

import com.example.spring_boot_controller.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {

    List<BlogPost> findByAppUser_Username(String username);

}
