package com.hxdavid.hxframework.utils.exception;

/**
 * @author hzhuangxin3@corp.netease.com, Huang Xin
 * @date 2016-07-12 11:46
 */
public enum ExceptionCodeEnum {
    FRONT_NOTIFIABLE(100, "前端文案异常")

    ;

    private Integer code;
    private String msg;

    private ExceptionCodeEnum(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static boolean isCodeValid(Integer code) {
        for (ExceptionCodeEnum e : values()) {
            if (e.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public static ExceptionCodeEnum getByCode(Integer code) {
        for (ExceptionCodeEnum e : values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }

    public static ExceptionCodeEnum getByMsg(String msg) {
        for (ExceptionCodeEnum e : values()) {
            if (e.getMsg().equals(msg)) {
                return e;
            }
        }
        return null;
    }
}
