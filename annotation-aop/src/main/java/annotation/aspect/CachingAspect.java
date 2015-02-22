package annotation.aspect;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created on 22/02/15.
 */
@Component
@Aspect
public class CachingAspect {

    private Cache<EqualityArrays, Object> cache = CacheBuilder.newBuilder()
            .recordStats()
            .maximumSize(20)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    @Around("@annotation(annotation.SensibleMethod)")
    public Object putSomeCache(final ProceedingJoinPoint joinPoint) throws Throwable {

        return cache.get(new EqualityArrays(joinPoint.getArgs()), () -> {
            try {
                return joinPoint.proceed();
            } catch (Throwable throwable) {
               throw new RuntimeException(throwable);
            }
        });
    }

    public Cache<EqualityArrays, Object> getCache() {
        return cache;
    }
}

class EqualityArrays {

    Object[] tab;

    public EqualityArrays(Object[] tab) {
        this.tab = tab;
    }

    @Override
    public boolean equals(Object obj) {
        return Arrays.deepEquals(this.tab, ((EqualityArrays) obj).tab);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(this.tab);
    }
}
