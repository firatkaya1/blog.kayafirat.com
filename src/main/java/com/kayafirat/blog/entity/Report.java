package com.kayafirat.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "report")
@JsonIgnoreProperties(ignoreUnknown = true, value = "targetType")
public class Report extends JdkSerializationRedisSerializer implements Serializable {

    @Id
    @GeneratedValue
    private Long reportId;

    @Column
    private String reportCode;

    @Column
    private String email;

    @Column
    private String reportMessage;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdDate;

    @Column
    private boolean isRead;


}
