package net.hicp.localhost.exception;

import lombok.extern.slf4j.Slf4j;
import net.hicp.localhost.exception.enums.MessageCodeEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @desc 全局异常处理类
 * @author cheat
 */

@ControllerAdvice
@Slf4j
public class GlobalControllerAdvice {
    @ExceptionHandler(value =RuntimeException.class)
    @ResponseBody
    public String exceptionHandler(Exception e){
        log.error(e.getMessage());
        e.printStackTrace();
        return MessageCodeEnum.EXCEPTION.toString();
    }
}
