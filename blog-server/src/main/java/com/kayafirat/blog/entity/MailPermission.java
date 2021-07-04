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

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mailPermission")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "targetType"})
public class MailPermission extends JdkSerializationRedisSerializer implements Serializable  {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private boolean loginAttempt;

    @Column
    private boolean loginNotification;

    @Column
    private boolean postNotification;

    @Column
    private boolean passChange;

}
