package com.hxdavid.hxframework.dao.domain;

import com.netease.edu.persist.domain.BaseDomain;
import com.netease.framework.dao.sql.annotation.DataProperty;

/**
 * @author hzhuangxin3@corp.netease.com, Huang Xin
 * @date 2016-08-17 15:04
 */
public class BookDomain extends BaseDomain {

    private static final long serialVersionUID = -6677452322246013257L;

    private Long   id;
    private String name;

    @DataProperty(column = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
