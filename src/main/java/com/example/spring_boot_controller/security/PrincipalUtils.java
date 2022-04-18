package com.example.spring_boot_controller.security;

import com.example.spring_boot_controller.entities.AppUser;
import com.example.spring_boot_controller.repositories.AppUserRepository;
import com.example.spring_boot_controller.views.BlogView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

@Service
public class PrincipalUtils {

    @Autowired
    AppUserRepository appUserRepository;

    public AppUser getAppUserFromFromPrincipal(){
        return appUserRepository.findAppUserByUsername(getName()).orElseThrow();
    }

    public static String getName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static boolean isAuthenticated(){
        return !SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName()
                .equalsIgnoreCase("anonymousUser");
    }

    public static void logout(){
        UI.getCurrent().navigate(BlogView.class);
        new SecurityContextLogoutHandler().logout(VaadinServletRequest.getCurrent().getHttpServletRequest(), null, null);
    }

}
