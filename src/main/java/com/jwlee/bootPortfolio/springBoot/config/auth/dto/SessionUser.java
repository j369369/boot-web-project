package com.jwlee.bootPortfolio.springBoot.config.auth.dto;


import com.jwlee.bootPortfolio.springBoot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;
    private String role;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
        this.role = user.getRole().name();
    }
}
