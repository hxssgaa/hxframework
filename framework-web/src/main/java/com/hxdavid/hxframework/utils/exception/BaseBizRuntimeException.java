package com.hxdavid.hxframework.utils.exception;

/**
 * @author hzhuangxin3@corp.netease.com, Huang Xin
 * @date 2016-07-12 11:40
 */
public abstract class BaseBizRuntimeException extends RuntimeException{

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public BaseBizRuntimeException() {
        this.code = ExceptionCodeEnum.FRONT_NOTIFIABLE.getCode();//普通业务异常
    }

    public BaseBizRuntimeException(ExceptionCodeEnum codeEnum) {
        super(codeEnum.getMsg());
        this.code = codeEnum.getCode();
    }

    public BaseBizRuntimeException(String message) {
        super(message);
        this.code = ExceptionCodeEnum.FRONT_NOTIFIABLE.getCode();
    }

    public BaseBizRuntimeException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public BaseBizRuntimeException(String message, Throwable cause, Integer code) {
        super(message, cause);
        this.code = code;
    }

    public BaseBizRuntimeException(String message, Throwable cause) {
        super(message, cause);
        this.code = ExceptionCodeEnum.FRONT_NOTIFIABLE.getCode();
    }

    public BaseBizRuntimeException(Throwable cause, Integer code) {
        super(cause);
        this.code = code;
    }

    public BaseBizRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
                                   Integer code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }
}
