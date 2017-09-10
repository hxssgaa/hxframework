package com.hxdavid.hxframework.utils.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author hzhuangxin3@corp.netease.com, Huang Xin
 * @date 2016-07-12 14:36
 */
@Aspect
public class RequestParamAspectj {

    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void allController() {
    }

//    @Around("allController()")
//    public Object beforeGetParameter(ProceedingJoinPoint point) throws Throwable {
//        MethodSignature signature = (MethodSignature) point.getSignature();
//        Method method = signature.getMethod();
//        Object[] args = point.getArgs();
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//        ObjectMapper objectMapper = new ObjectMapper();
//        for (int i = 0; i < args.length; i++) {
//            MethodParameter methodParam = new MethodParameter(method, i);
//            String paramName = null;
//            String value = null;
//            Annotation[] paramAnns = methodParam.getParameterAnnotations();
//            if (ArrayUtils.isEmpty(paramAnns)) {
//                continue;
//            }
//            for (Annotation paramAnn : paramAnns) {
//                if (!RequestParam.class.isInstance(paramAnn)) {
//                    continue;
//                }
//                RequestParam requestParam = (RequestParam) paramAnn;
//                paramName = requestParam.value();
//                value = request.getParameter(paramName);
//                if (value == null && !requestParam.required()) {//允许字段为空
//                    continue;
//                }
//                if (value == null && requestParam.required()) {
//                    throw new CommonFrontNotifiableException("传入参数字段不允许为空");
//                }
//                if (String.class.equals(methodParam.getParameterType())) {
//                    args[i] = value;
//                } else {
//                    try {
//                        args[i] = objectMapper.readValue(value, methodParam.getParameterType());
//                    } catch (Exception e) {
//                        throw new CommonFrontNotifiableException("传入参数解析失败", e);
//                    }
//                }
//            }
//        }
//        return point.proceed(args);
//    }
}
