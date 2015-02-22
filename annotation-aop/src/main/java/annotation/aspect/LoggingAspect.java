package annotation.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @AfterReturning(pointcut = "@annotation(annotation.SensibleMethod)",
                    returning="returnValue")
    public void logUsage(JoinPoint joinPoint, Object returnValue){
        logger.debug("Calling : {} with arguments", joinPoint.getSignature().getName());
        for (int index = 0; index < joinPoint.getArgs().length; index++) {
            logger.debug("[{}] : {}", index, joinPoint.getArgs()[index]);
        }
        logger.debug("And return : {}", returnValue);
    }

}
