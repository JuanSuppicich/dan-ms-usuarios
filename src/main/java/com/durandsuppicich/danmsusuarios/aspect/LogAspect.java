package com.durandsuppicich.danmsusuarios.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(* com.durandsuppicich.danmsusuarios.service.*.*(..))")
    private void metodosServicios() {}

    @Pointcut("execution(* com.durandsuppicich.danmsusuarios.dao.*.*(..))")
    private void metodosRepositorios() {}

    @Before("metodosServicios() || metodosRepositorios()" )
    public void hacerAntes(JoinPoint joinPoint) {
        logger.debug("Metodo a ejecutar: " + joinPoint.getSignature().getName() + 
        " en: " + joinPoint.getTarget().getClass() + 
        " con args: " + Arrays.toString(joinPoint.getArgs()));
    }

    @After("metodosServicios() || metodosRepositorios()")
    public void hacerDespues(JoinPoint joinPoint) {
        logger.debug("Metodo ejecutado: " + joinPoint.getSignature().getName() + 
        " en: " + joinPoint.getTarget().getClass() + 
        " con args: " + Arrays.toString(joinPoint.getArgs()));
    }
}
