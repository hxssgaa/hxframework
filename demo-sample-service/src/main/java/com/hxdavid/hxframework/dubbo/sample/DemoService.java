package com.hxdavid.hxframework.dubbo.sample;

import com.hxdavid.hxframework.dubbo.sample.dto.HelloParameterDto;
import com.hxdavid.hxframework.dubbo.sample.dto.HelloResultDto;

/**
 * @author hzhuangxin3@corp.netease.com, Huang Xin
 * @date 2016-06-24 10:45
 */
public interface DemoService {

    HelloResultDto sayHello(HelloParameterDto name);
}
