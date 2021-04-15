package com.kayafirat.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "userProfile")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "targetType"})
public class UserProfile extends JdkSerializationRedisSerializer implements Serializable  {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String contactAddress;

    @Column
    private boolean accountStatus;

    @Column
    private Date birthDate;

    @Column
    private Date registerDate;

    @Column
    private String githubAddress;

    @Column
    private String linkedinAddress;

}
