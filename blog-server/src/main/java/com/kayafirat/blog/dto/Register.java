package com.kayafirat.blog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Register {
    private String username;
    private String emailAddress;
    private String password;
}
