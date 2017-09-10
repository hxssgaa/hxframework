package com.hxdavid.hxframework.sampleweb.service;

import com.hxdavid.hxframework.sampleweb.dto.BlockStuffDto;

/**
 * @author hzhuangxin3@corp.netease.com, Huang Xin
 * @date 2016-06-15 13:54
 */
public interface BlackListService {

    /**
     * 新增一个黑名单记录
     * @param blockStuffDto 黑名单记录
     */
    void insertBlockStuff(BlockStuffDto blockStuffDto);

    /**
     * 删除一个黑名单记录
     * @param id 黑名单id
     */
    void removeBlockStuff(Long id);

    String testDoingSomething(Long id);

}
