package com.kayafirat.blog.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kayafirat.blog.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "targetType"})
public class UserProfileDTO extends JdkSerializationRedisSerializer implements Serializable {

    private long id;
    private String email;
    private String username;
    private String photo;
    private String contactAddress;
    private Date birthDate;
    private Date registerDate;
    private String githubAddress;
    private String linkedinAddress;
    private boolean accountStatus;

    public UserProfileDTO(User user){
        //Eğer kullanıcı authenticate edilmemişse permission ayarlarını uygula.
        //Ama kullanıcı authenticate edildiyse her şeyi göster
        if(SecurityContextHolder.getContext().getAuthentication() != null){
            this.linkedinAddress = user.getUserProfile().getLinkedinAddress();
            this.githubAddress = user.getUserProfile().getGithubAddress();
            this.contactAddress = user.getUserProfile().getContactAddress();
            this.birthDate = user.getUserProfile().getBirthDate();
            this.registerDate = user.getUserProfile().getRegisterDate();
            this.id = user.getId();
            this.email = user.getEmail();
            this.username = user.getUsername();
            this.photo = user.getPhoto();
            this.accountStatus = user.getUserProfile().isAccountStatus();
        } else {
            this.linkedinAddress = (user.getUserPermission().isLinkedin()) ? null : user.getUserProfile().getLinkedinAddress();
            this.githubAddress = (user.getUserPermission().isGithub()) ? null : user.getUserProfile().getGithubAddress();
            this.contactAddress = (user.getUserPermission().isContact()) ? null : user.getUserProfile().getContactAddress();
            this.birthDate = (user.getUserPermission().isBirthdate()) ? null : user.getUserProfile().getBirthDate();
            this.registerDate = user.getUserProfile().getRegisterDate();
            this.id = user.getId();
            this.email = user.getEmail();
            this.username = user.getUsername();
            this.photo = user.getPhoto();
            //bunu sileceksin.
            this.accountStatus = user.getUserProfile().isAccountStatus();

        }

    }

    public UserProfileDTO() { }

}
