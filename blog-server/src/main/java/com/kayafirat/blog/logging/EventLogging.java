package com.kayafirat.blog.logging;

import com.kayafirat.blog.entity.Log;
import com.kayafirat.blog.enums.LogType;
import com.kayafirat.blog.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Aspect
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventLogging {

    private final LogRepository logRepository;


}