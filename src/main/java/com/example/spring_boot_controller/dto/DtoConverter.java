package com.example.spring_boot_controller.dto;

import com.example.spring_boot_controller.entities.AppUser;
import com.example.spring_boot_controller.entities.BlogPost;
import com.example.spring_boot_controller.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DtoConverter {

    @Autowired
    AppUserRepository appUserRepository;

    public BlogPost RequestDtoToEntity(BlogRequestDTO blogRequestDTO){

        AppUser appUser = appUserRepository
                        .findById(blogRequestDTO
                        .appUserId()).orElseThrow();

        return new BlogPost(
                blogRequestDTO.title(),
                blogRequestDTO.message(),
                appUser
                );
    }

    public BlogResponseDTO entityToResponseDto(BlogPost blogPost){
        return new BlogResponseDTO(
                blogPost.getId(),
                blogPost.getTitle(),
                blogPost.getMessage(),
                blogPost.getAppUser().getId()
        );
    }

}
