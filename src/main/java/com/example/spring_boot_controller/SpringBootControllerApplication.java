package com.example.spring_boot_controller;

import com.example.spring_boot_controller.entities.AppUser;
import com.example.spring_boot_controller.entities.BlogPost;
import com.example.spring_boot_controller.repositories.AppUserRepository;
import com.example.spring_boot_controller.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class SpringBootControllerApplication implements CommandLineRunner {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    BlogPostRepository blogPostRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootControllerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        AppUser Gunnar = new AppUser("Gunnar", passwordEncoder.encode("pass"));
        AppUser Alice = new AppUser("Alice", passwordEncoder.encode("pass"));
        System.out.println("Krypterat lösenord med BCrypt: " + Gunnar.getPassword());
        appUserRepository.saveAll(List.of(Gunnar, Alice));

        BlogPost blogPost = new BlogPost("Hej världen", "Det är sol ute", Gunnar);
        BlogPost blogPost2 = new BlogPost("Sommar!", "Köpte glass idag", Gunnar);
        BlogPost blogPost3 = new BlogPost("Hej från Alice", "Ville bara säg hej jag med", Alice);
        blogPostRepository.saveAll(List.of(blogPost, blogPost2, blogPost3));

    }
}
