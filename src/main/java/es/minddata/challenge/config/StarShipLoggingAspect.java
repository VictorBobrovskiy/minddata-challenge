package es.minddata.challenge.config;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StarShipLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(StarShipLoggingAspect.class);

    @Before("execution(es.minddata.challenge.service.StarShipService.findStarShipById(Long)) && args(id)")
    public void logIfNegativeId(Long id) {
        if (id != null && id < 0) {
            logger.warn("Se intentó acceder a una nave con un ID negativo: {}", id);
        }
    }
}