package com.kayafirat.blog.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kayafirat.blog.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mailQueue")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MailQueue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long queueId;

    @Column
    private String emailAddress;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdDate;

    @Column
    private Date sendDate;

    @Column
    private boolean isSend;

    @Column
    private Type mailType;

}
