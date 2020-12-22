package net.hicp.localhost.aop;

import lombok.extern.slf4j.Slf4j;
import net.hicp.localhost.aop.annotation.AopAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author cheat
 * @updateTime 2020-12-22 16:47
 * @desc
 */
@Aspect
@Component
@Slf4j
public class LogAspect {
    /**
     * 定义切入点，切入点
     *通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("execution(public * net.hicp.localhost.control.TestControl.test(String)) && args(name))")
    public void testAspect(String name){

    }

    /**
     * @description  在连接点执行之前执行的通知
     */
    @Before("testAspect(name)")
    public void doBefore(JoinPoint joinPoint,String name){
        log.info("doBefore 在连接点执行之前执行的通知 {}",name);
    }

    /**
     * @description  在连接点执行之后执行的通知（返回通知和异常通知的异常）
     */
    @After("testAspect(name)")
    public void doAfter(JoinPoint joinPoint,String name){
        log.info("doAfter在连接点执行之后执行的通知 {}",name);
    }

    /**
     * @description  在连接点执行之后执行的通知（返回通知）
     */
    @AfterReturning("testAspect(name)")
    public void doAfterReturning(JoinPoint joinPoint,String name){
        log.info("doAfterReturning在连接点执行之后执行的通知 {}",name);
    }

    /**
     * @description  在连接点执行之后执行的通知（异常通知）
     */
    @AfterThrowing("testAspect(name)")
    public void doAfterThrowing(JoinPoint joinPoint,String name){
        log.info("doAfterThrowing 在连接点执行之后执行的通知{}",name);
    }

    /**
     * @description  使用环绕通知
     */
    @Around("testAspect(name)")
    public Object doAroundData(ProceedingJoinPoint pjp, String name) throws Throwable {
        log.info("doAroundBefore 环绕通知 {}",name);
        Object k = pjp.proceed();


        log.info("doAroundAfter 环绕通知 {}",name);

        return k;
    }


    /**
     * 自定义注解形式
     */
    @Pointcut("@annotation(net.hicp.localhost.aop.annotation.AopAnnotation)")
    public void testAspect2(){

    }

    /**
     * 使用前置通知
     * Spring AOP支持的AspectJ切入点指示符
     * 切入点指示符用来指示切入点表达式目的，，在Spring AOP中目前只有执行方法这一个连接点，Spring AOP支持的AspectJ切入点指示符如下：
     *
     * execution：用于匹配方法执行的连接点；
     *
     * within：用于匹配指定类型内的方法执行；
     *
     * this：用于匹配当前AOP代理对象类型的执行方法；注意是AOP代理对象的类型匹配，这样就可能包括引入接口也类型匹配；
     *
     * target：用于匹配当前目标对象类型的执行方法；注意是目标对象的类型匹配，这样就不包括引入接口也类型匹配；
     *
     * args：用于匹配当前执行的方法传入的参数为指定类型的执行方法；
     *
     * @within：用于匹配所以持有指定注解类型内的方法；
     *
     * @target：用于匹配当前目标对象类型的执行方法，其中目标对象持有指定的注解；
     *
     * @args：用于匹配当前执行的方法传入的参数持有指定注解的执行；
     *
     * @annotation：用于匹配当前执行方法持有指定注解的方法；
     *
     * bean：Spring AOP扩展的，AspectJ没有对于指示符，用于匹配特定名称的Bean对象的执行方法；
     *
     * reference pointcut：表示引用其他命名切入点，只有@ApectJ风格支持，Schema风格不支持。
     *
     * AspectJ切入点支持的切入点指示符还有：
     * call、get、set、preinitialization、staticinitialization、initialization、handler、
     * adviceexecution、withincode、cflow、cflowbelow、if、@this、@withincode；但Spring AOP目前不支持这些指示符，
     * 使用这些指示符将抛出IllegalArgumentException异常。这些指示符Spring AOP可能会在以后进行扩展。
     * @param joinPoint
     * @param aopAnnotation
     * @param name
     */
    //在Spring AOP中，除了execution和bean指示符不能传递参数给通知方法，其他指示符都可以将匹配的相应参数或对象自动传递给通知方法。
    @Before(value = "@annotation(aopAnnotation) && target(bean)&& args(name) && this(aThis)",argNames = "joinPoint,bean,aopAnnotation,name,aThis")
    public void doBefore(JoinPoint joinPoint,Object bean, AopAnnotation aopAnnotation,String name,Object aThis){
        log.info("自定义注解形式before aopAnnotation:{},bean:{}, name:{},this:{}",aopAnnotation,bean,name,aThis);
    }

    /**
     * @description  使用环绕通知
     */
    //@Around("@annotation(aopAnnotation) && args(name)")
    public Object doAroundData2(ProceedingJoinPoint pjp, AopAnnotation aopAnnotation,String name) throws Throwable {
        log.info("自定义注解形式doAroundBefore 环绕通知 aopAnnotation:{} , name:{} {}",aopAnnotation,name);
        Object k = pjp.proceed();
        log.info("自定义注解形式 doAroundAfter 环绕通知 aopAnnotation:{} , name:{}",aopAnnotation,name);


        log.info("被通知方法参数列表 args:{}",pjp.getArgs());
        log.info("连接点 {}",pjp.getKind());
        log.info("当前连接点签名 {}",pjp.getSignature());
        log.info("连接点方法所在类文件中的位置 {}",pjp.getSourceLocation());
        log.info("连接点静态部分 {}",pjp.getStaticPart());
        log.info("目标对象 {}",pjp.getTarget());
        log.info("AOP代理对象 {}",pjp.getThis());


        return k;
    }
}
