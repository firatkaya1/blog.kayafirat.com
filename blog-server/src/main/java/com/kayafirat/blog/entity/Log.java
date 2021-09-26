package com.kayafirat.blog.entity;

import com.kayafirat.blog.enums.LogType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "log")
public class Log{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long logId;

    private Date createdDate;

    private LogType logType;

    private String logTitle;

    private Long logTime;
    private String className;
    private String methodName;

    @Column(columnDefinition = "TEXT")
    private String logMessage;

    public Log(LogType logType,String logTitle,String logMessage,long time,String methodName,String className){
        this.logType = logType;
        this.logTitle = logTitle;
        this.logMessage = logMessage;
        this.logTime = time;
        this.className = className;
        this.methodName = methodName;
    }

    public Log(LogType logType,String logTitle,String logMessage){
        this.logType = logType;
        this.logTitle = logTitle;
        this.logMessage = logMessage;
    }

}
