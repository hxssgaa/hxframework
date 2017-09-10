package com.hxdavid.hxframework.sampleweb.service.impl;

import com.hxdavid.hxframework.commons.mongo.BaseMongoServiceImpl;
import com.hxdavid.hxframework.sampleweb.dto.BlockStuffDto;
import com.hxdavid.hxframework.sampleweb.service.BlackListService;
import org.springframework.stereotype.Service;

/**
 * @author hzhuangxin3@corp.netease.com, Huang Xin
 * @date 2016-06-15 14:13
 */
@Service
public class BlackListServiceImpl extends BaseMongoServiceImpl<BlockStuffDto, Long> implements BlackListService {

//    @Autowired
//    public void setMorphiaBean(MorphiaBean morphiaBean) {
//        setBaseMorphia(morphiaBean);
//    }

    @Override
    public void insertBlockStuff(BlockStuffDto blockStuffDto) {
//        getDataStore().save(blockStuffDto);
    }

    @Override
    public void removeBlockStuff(Long id) {
//        Query<BlockStuffDto> query = getDataStore()
//                .createQuery(BlockStuffDto.class)
//                .filter("_id = ", id);
//        getDataStore().delete(query);
    }

    @Override
    public String testDoingSomething(Long id) {
        return "asdada" + id;
    }
}
