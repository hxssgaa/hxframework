package com.hxdavid.hxframework.utils.common;

import org.apache.commons.lang.Validate;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hzhuangxin3@corp.netease.com, Huang Xin
 * @date 2016-06-14 16:31
 */
public class BeanUtils {

    /**
     * 将origin类转换到结果类
     * @param origin origin类
     * @param tClazz 目的class
     * @return 拷贝的结果类
     */
    public static <T,E> T convert(E origin,Class<T> tClazz){
        Validate.notNull(origin);
        try {
            T t = tClazz.newInstance();
            BeanCopier copier = BeanCopier.create(origin.getClass(), tClazz, false);
            copier.copy(origin, t, null);
            return t;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    /**
     * 批量转换origin类到结果类列表
     * @param origins origin类列表
     * @param tClazz 目的class
     * @return 拷贝的结果类列表
     */
    public static <T,E> List<T> convertBatch(List<E> origins, Class<T> tClazz){
        if (CollectionUtils.isEmpty(origins)) {
            return new ArrayList<T>(0);
        }
        List<T> ts = new ArrayList<T>();
        for (E e : origins) {
            ts.add(convert(e,tClazz));
        }
        return ts;
    }
}
