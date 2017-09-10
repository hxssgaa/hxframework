package com.hxdavid.hxframework.utils.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Web端异常处理
 * 
 * @author hzliliangdong
 * 
 */
public class CommonExceptionHandler implements HandlerExceptionResolver {

    private Logger log = LoggerFactory.getLogger(CommonExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        ModelAndView mv = new ModelAndView();
        log.error(getMessageByException(ex)); // 打印日志信息
        if (isFrontException(ex)) { // 是否为前段异常
            addResponseInfo(response, ex);
            return mv;
        } else { // 其他异常跳到主页
            writeResponse(response, "服务繁忙");
            return mv;
        }
    }

    private void writeResponse(HttpServletResponse response, String info) {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write("{\"code\":500,\"message\":\"" + info + "\"}");
        } catch (IOException e) {
            log.error("网络异常:" + e.getMessage(), e);
        }
    }

    private void addResponseInfo(HttpServletResponse response, Exception ex) {
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        writeResponse(response , ex.getMessage());
    }

    private boolean isFrontException(Throwable ex) {
        return AnnotationUtils.findAnnotation(ex.getClass(), FrontNotifiable.class) != null;
    }

    /**
     * 获取错误日志信息
     * 
     * @param e
     * @return
     */
    public static String getMessageByException(Exception e) {
        StringBuilder error = new StringBuilder();
        error.append(e.toString() + "\n");
        for (StackTraceElement s : e.getStackTrace()) {
            error.append(s.toString() + "\n");
        }
        return error.toString();
    }

}
