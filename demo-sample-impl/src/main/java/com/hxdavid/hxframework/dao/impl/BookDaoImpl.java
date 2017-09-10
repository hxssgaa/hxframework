package com.hxdavid.hxframework.dao.impl;

import com.hxdavid.hxframework.dao.BookDao;
import com.hxdavid.hxframework.dao.domain.BookDomain;
import com.netease.edu.persist.dao.annotation.DomainMetadata;
import com.netease.edu.persist.dao.sql.BaseDaoSqlImpl;
import org.springframework.stereotype.Component;

/**
 * @author hzhuangxin3@corp.netease.com, Huang Xin
 * @date 2016-08-17 15:13
 */
@DomainMetadata(domainClass = BookDomain.class, tableName = "book", idColumn = "id", idProperty = "id")
@Component("bookDao")
public class BookDaoImpl extends BaseDaoSqlImpl<BookDomain> implements BookDao{

}
