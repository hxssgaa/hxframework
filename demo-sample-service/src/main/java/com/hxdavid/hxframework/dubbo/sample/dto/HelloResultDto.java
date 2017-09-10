package com.hxdavid.hxframework.dubbo.sample.dto;

import java.io.Serializable;

/**
 * @author hzhuangxin3@corp.netease.com, Huang Xin
 * @date 2016-08-16 20:03
 */
public class HelloResultDto implements Serializable{

    private static final long serialVersionUID = 200000000001L;

    private String r1;
    private Integer r2;
    private Integer r3;

    public String getR1() {
        return r1;
    }

    public void setR1(String r1) {
        this.r1 = r1;
    }

    public Integer getR2() {
        return r2;
    }

    public void setR2(Integer r2) {
        this.r2 = r2;
    }

    public Integer getR3() {
        return r3;
    }

    public void setR3(Integer r3) {
        this.r3 = r3;
    }

    @Override
    public String toString() {
        return "HelloResultDto{" +
               "r1='" + r1 + '\'' +
               ", r2=" + r2 +
               ", r3=" + r3 +
               '}';
    }
}
