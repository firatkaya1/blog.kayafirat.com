package com.kayafirat.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailDTO {

    private Long userId;
    private String username;
    private String email;
    private Date birthDate;

}
