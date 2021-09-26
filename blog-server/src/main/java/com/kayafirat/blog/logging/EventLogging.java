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

    @Around("execution(* com.kayafirat.blog.service..*(..)))")
    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        final StopWatch stopWatch = new StopWatch();

        //Measure method execution time
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        //Log method execution time
        String message = className+ " adındaki clasın sahip olduğu " + methodName + " isimli methoda istek yapıldı. Harcanan süre : " + stopWatch.getTotalTimeMillis() + " ms";
        Log log = new Log(LogType.INFO,"EVENT LOG",message,stopWatch.getTotalTimeMillis(),methodName,className);
        logRepository.save(log);
        return result;
    }
}