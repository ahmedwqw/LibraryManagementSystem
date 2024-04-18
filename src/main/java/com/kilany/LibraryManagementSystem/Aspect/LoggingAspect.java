package com.kilany.LibraryManagementSystem.Aspect;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Pointcut("execution(* com.kilany.LibraryManagementSystem.Services.BookService.*(..))")
    private void bookOperation() {}

    @Pointcut("execution(* com.kilany.LibraryManagementSystem.Services.PatronService.*(..))")
    private void patronOperation() {}

    @Pointcut("execution(* com.kilany.LibraryManagementSystem.Services.BorrowingRecordService.*(..))")
    private void borrowingRecordOperation() {}

    // Logging method calls
    @Before("bookOperation() || patronOperation() || borrowingRecordOperation()")
    public void logMethodCall() {
        logger.info("Method called");
    }

    // Logging exceptions
    @AfterThrowing(pointcut = "bookOperation() || patronOperation() || borrowingRecordOperation()", throwing = "e")
    public void logException(Exception e) {
        logger.error("Exception occurred: " + e.getMessage());
    }

    // Logging method return values
    @AfterReturning(pointcut = "bookOperation() || patronOperation() || borrowingRecordOperation()", returning = "result")
    public void logReturnValue(Object result) {
        logger.info("Method returned: " + result);
    }
}

