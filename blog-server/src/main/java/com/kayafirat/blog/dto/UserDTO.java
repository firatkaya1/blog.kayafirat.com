package com.kayafirat.blog.dto;

import com.kayafirat.blog.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long userId;
    private String username;
    private String email;
    private Date createdDate;
    private Boolean accountStatus;
    private Set<RoleDTO> role;

    public UserDTO(User user){
        this.userId = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.createdDate = (user.getUserProfile() != null) ? user.getUserProfile().getRegisterDate() : null;
        this.accountStatus =  (user.getUserProfile() != null) ? user.getUserProfile().isAccountStatus() : null;
        if(user.getRole() != null){
            role = new HashSet<>();
            user.getRole().forEach(r -> this.role.add(new RoleDTO(r)));

        }

    }
}
