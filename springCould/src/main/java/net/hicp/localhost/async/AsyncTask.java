package net.hicp.localhost.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * 功能描述：异步任务业务类（@Async也可添加在方法上，@Async放在类上，表示类内方法皆为异步方法）
 */
@Component
@Async("taskExecutor")//使用自己的线程池记得括号内填写线程池bean的名字，详见下边线程池配置；如果使用默认线程池，则直接使用@Async
@Slf4j
public class AsyncTask {
    
 
    //获取异步结果
    public Future<String> task4() throws InterruptedException{
        long begin = System.currentTimeMillis();
        Thread.sleep(2000L);
        long end = System.currentTimeMillis();
        log.info("任务4耗时={}",(end-begin));
        return new AsyncResult<String>("任务4");
    }
    
    
    public Future<String> task5() throws InterruptedException{
        long begin = System.currentTimeMillis();
        Thread.sleep(3000L);
        long end = System.currentTimeMillis();
        log.info("任务5耗时={}",(end-begin));
        return new AsyncResult<String>("任务5");
    }
    
    public Future<String> task6() throws InterruptedException{
        long begin = System.currentTimeMillis();
        Thread.sleep(1000L);
        long end = System.currentTimeMillis();
        log.info("任务6耗时={}",(end-begin));
        return new AsyncResult<String>("任务6");
    }
        
}