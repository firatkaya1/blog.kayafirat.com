package com.kayafirat.blog.dto;

import com.kayafirat.blog.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RoleDTO {

    private Long roleId;
    private String roleName;

    public RoleDTO(Role role){
        this.roleId = role.getId();
        this.roleName = role.getRole();
    }
}
