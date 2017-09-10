package com.hxdavid.hxframework.service.impl;

import com.hxdavid.hxframework.dao.BookDao;
import com.hxdavid.hxframework.dao.domain.BookDomain;
import com.hxdavid.hxframework.dubbo.sample.DemoService;
import com.hxdavid.hxframework.dubbo.sample.dto.HelloParameterDto;
import com.hxdavid.hxframework.dubbo.sample.dto.HelloResultDto;

import javax.annotation.Resource;

/**
 * @author hzhuangxin3@corp.netease.com, Huang Xin
 * @date 2016-06-24 10:46
 */
public class DemoServiceImpl implements DemoService {

    @Resource
    private BookDao bookDao;

    @Override
    public HelloResultDto sayHello(HelloParameterDto name) {
        HelloResultDto resultDto = new HelloResultDto();
        resultDto.setR1(name.getP1());
        resultDto.setR2(name.getP2());
        BookDomain newEntity = new BookDomain();
        newEntity.setName(name.getP1());
        bookDao.add(newEntity);
        return resultDto;
    }
}
