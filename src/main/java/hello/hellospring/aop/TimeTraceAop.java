package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * AOP(  Aspect Oriented Programming )
 * 공통 관심 사항(cross-cutting concern) vs 핵심 관심 사항(core concern)
 * 공통관심사항 => 시작시간과 끝나는시간의 차이
 * 핻심관심사항 => 비즈니스로직
 * AOP를 사용하면 기존의 비즈니스 코드를 만지지 않고 공통관심사항을 적용시킬수 있다.
 * AOP를 적용시키면 컨트롤러가 직접 서비스를 불러오는것이 아니라 프록시를 불러오고 프록시가 서비스를 불러오는 구조
 */
@Aspect
@Component
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws  Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
