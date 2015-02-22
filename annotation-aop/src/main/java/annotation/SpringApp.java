package annotation;

import annotation.aspect.CachingAspect;
import annotation.service.BusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

public class SpringApp {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(SpringApp.class);

        ApplicationContext context = new AnnotationConfigApplicationContext("annotation");

        BusinessService businessService = context.getBean(BusinessService.class);


        logger.debug("{}", businessService.searchForBook("Asimov"));
        logger.debug("{}", businessService.searchForBook("Asimov"));
        logger.debug("{}", businessService.searchForBook("Herbert"));
        logger.debug("{}", businessService.searchForBook("Asimov"));
        logger.debug("{}", businessService.searchForBook("Brown"));
        logger.debug("{}", businessService.searchForBook("Asimov"));
        logger.debug("{}", businessService.searchForBook("Asimov"));
        logger.debug("{}", businessService.searchForBook("Herbert"));
        logger.debug("{}", businessService.searchForBook("Brown"));
        logger.debug("{}", businessService.searchForBook("Asimov"));
        logger.debug("{}", businessService.searchForBook("Herbert"));

        CachingAspect cachingAspect = context.getBean(CachingAspect.class);
        logger.debug("{}", cachingAspect.getCache().stats());

    }
}

@Configuration
@EnableAspectJAutoProxy
class AopConfiguration {

}
