package com.hxdavid.hxframework.utils.exception;

/**
 * @author hzhuangxin3@corp.netease.com, Huang Xin
 * @date 2016-07-12 11:44
 */
public abstract class BaseBizException extends Exception{

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public BaseBizException() {
        this.code = ExceptionCodeEnum.FRONT_NOTIFIABLE.getCode();//普通业务异常
    }

    public BaseBizException(String message) {
        super(message);
        this.code = ExceptionCodeEnum.FRONT_NOTIFIABLE.getCode();
    }

    public BaseBizException(ExceptionCodeEnum codeEnum) {
        super(codeEnum.getMsg());
        this.code = codeEnum.getCode();
    }

    public BaseBizException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public BaseBizException(String message, Throwable cause, Integer code) {
        super(message, cause);
        this.code = code;
    }

    public BaseBizException(String message, Throwable cause) {
        super(message, cause);
        this.code = ExceptionCodeEnum.FRONT_NOTIFIABLE.getCode();
    }

    public BaseBizException(Throwable cause, Integer code) {
        super(cause);
        this.code = code;
    }

    public BaseBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
                            Integer code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }
}
