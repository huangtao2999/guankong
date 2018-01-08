package com.dsw.guankong.sys;

import com.dsw.guankong.util.ActionResult;
import com.dsw.guankong.util.BizException;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;

/**
 * 集中异常处理类
 */
@RestController
@ControllerAdvice
public class BizExceptionHandler {
    protected static final Logger logger = Logger.getLogger(BizExceptionHandler.class);
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public Object bizExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        logger.error(MessageFormat.format("---BizException Handler---Host {0} invokes url {1}", req.getRemoteHost(), req.getRequestURL()));
        ActionResult actionResult = new ActionResult();
        actionResult.setSuccess(false);
        actionResult.setErrorMsg(e.getMessage());
        return actionResult;
    }
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object exceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        logger.error(MessageFormat.format("---Exception Handler---Host {0} invokes url {1} ERROR: {2}", req.getRemoteHost(), req.getRequestURL(), e.getMessage()),e);
        ActionResult actionResult = new ActionResult();
        actionResult.setSuccess(false);
        actionResult.setErrorCode("00000");
        actionResult.setErrorLevel(0);
        actionResult.setErrorMsg("系统错误,请联系管理员!");
        return actionResult;
    }
}
