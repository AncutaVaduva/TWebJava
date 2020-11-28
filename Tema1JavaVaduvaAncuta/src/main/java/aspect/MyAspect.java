package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


@Aspect
@Component
public class MyAspect {
    //doesn't work properly
    //private Logger logger= Logger.getLogger(MyAspect.class.getName());

    @Around("execution(* service.BookService.*(..))")
    public void logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        Object[] arguments = joinPoint.getArgs();
        System.out.println("\nBefore executing "+methodName+ " with parameters:");
        for (Object argument: arguments) {
            System.out.println("\tArgument: " + argument);
        }

        StopWatch stopWatch = new StopWatch(className + "->" + methodName);
        stopWatch.start(methodName);
        joinPoint.proceed();
        stopWatch.stop();

        System.out.println(stopWatch);
        System.out.println("After execution!\n");
    }

    @AfterThrowing(pointcut = "execution(* service.BookService.*(..))",throwing="error")
    public void logAfterThrowing(Exception error){
        System.out.println("An exception was thrown:\n\t"+error);
    }
}
