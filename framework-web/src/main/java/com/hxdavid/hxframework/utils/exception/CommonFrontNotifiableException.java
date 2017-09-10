package com.hxdavid.hxframework.utils.exception;

/**
 * @author hzhuangxin3@corp.netease.com, Huang Xin
 * @date 2016-07-12 14:02
 */
@FrontNotifiable
public class CommonFrontNotifiableException extends BaseBizRuntimeException {

    public CommonFrontNotifiableException() {
    }

    public CommonFrontNotifiableException(ExceptionCodeEnum codeEnum) {
        super(codeEnum);
    }

    public CommonFrontNotifiableException(String message) {
        super(message);
    }

    public CommonFrontNotifiableException(String message, Integer code) {
        super(message, code);
    }

    public CommonFrontNotifiableException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonFrontNotifiableException(String message, Throwable cause, Integer code) {
        super(message, cause, code);
    }
}
