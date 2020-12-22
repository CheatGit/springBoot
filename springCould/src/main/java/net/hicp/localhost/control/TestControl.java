package net.hicp.localhost.control;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.hicp.localhost.aop.annotation.AopAnnotation;
import net.hicp.localhost.async.AsyncTask;
import net.hicp.localhost.config.entity.UserProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

@RestController
@Slf4j
public class TestControl {
    @Autowired
    @Lazy
    private AsyncTask task;
    @Lazy
    @Autowired
    private UserProperties userProperties;



    /**
     * 异步线程测试
     * @return
     * @throws InterruptedException
     */
    @GetMapping("async_task")
    public String exeTask() throws InterruptedException{

        long begin = System.currentTimeMillis();

        Future<String> task4 = task.task4();
        Future<String> task5 = task.task5();
        Future<String> task6 = task.task6();

        /**
         *如果需要监控异步任务是否完成，可以使用以下方式
         **/
        //如果都执行完成就可以跳出循环,isDone方法如果此任务完成，true
        for(;;){
            if (task4.isDone() && task5.isDone() && task6.isDone()) {
                break;
            }
        }

        long end = System.currentTimeMillis();
        long total = end-begin;
        log.debug("执行总耗时={}",total);
        return String.valueOf(total);
    }

    @GetMapping("/test")
    public String test(@RequestParam("name") String name) {
        return "test:".concat(name);
    }
    @GetMapping("/test2")
    @AopAnnotation("aop测试")
    public String test2(@RequestParam("name") String name) {
        return "test2:".concat(name);
    }
    @GetMapping("/test3")
    public String test3() {
        log.info("bxh {}", JSONObject.toJSON(userProperties));
        return "test3:"+ System.currentTimeMillis();
    }

}
