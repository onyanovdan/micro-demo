package com.example.demo.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
public class ParamLoggingAspect {
    private Logger logger = Logger.getLogger(ParamLoggingAspect.class.getName());

    @Before("@annotation(LogAllParams)")
    public void logMethodInputParam(JoinPoint joinPoint) {
        CodeSignature methodSignature = (CodeSignature) joinPoint.getSignature();
        String name = joinPoint.getSignature().getName();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Method: ").append(name).append("; Input params: ");

        String[] in_params = methodSignature.getParameterNames();
        Object[] in_values = joinPoint.getArgs();
        for (int i = 0; i < in_params.length; i++) {
            stringBuilder.append(in_params[i]).append("=").append(in_values[i]).append(";");
        }
        logger.info(stringBuilder.toString());
    }

    @AfterReturning(value = "@annotation(LogAllParams)", returning = "result")
    public void logMethodOutputParam(JoinPoint joinPoint, Object result) {
        String name = joinPoint.getSignature().getName();
        logger.info("Method: " + name + "; Output params: " + result.toString());
    }
}
