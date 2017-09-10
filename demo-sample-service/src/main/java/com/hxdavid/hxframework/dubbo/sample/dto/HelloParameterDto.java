package com.hxdavid.hxframework.dubbo.sample.dto;

import java.io.Serializable;

/**
 * @author hzhuangxin3@corp.netease.com, Huang Xin
 * @date 2016-08-16 20:03
 */
public class HelloParameterDto implements Serializable{
    private String p1;
    private Integer p2;

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public Integer getP2() {
        return p2;
    }

    public void setP2(Integer p2) {
        this.p2 = p2;
    }

    @Override
    public String toString() {
        return "HelloParameterDto{" +
               "p1='" + p1 + '\'' +
               ", p2=" + p2 +
               '}';
    }
}
