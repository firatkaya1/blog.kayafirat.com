package com.kayafirat.blog.dto;

import com.kayafirat.blog.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MailDTO {

    private Type type;
    private String mailTitle;
    private String mailsubtitle;
    private String mailBody;
    private Long[] userIds;
    private Boolean sendAll;
}
