package com.hxdavid.hxframework.utils.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author hzhuangxin3@corp.netease.com, Huang Xin
 * @date 2016-06-14 16:29
 */
public class CommonUtils {

    private static final Logger LOG = LoggerFactory.getLogger(CommonUtils.class);

    /**
     * 根据列表中的某一属性, 获得该属性到列表对应元素的map集合
     * @param list 列表
     * @param propertyName 属性名称
     * @return 该属性到列表对应元素的map集合
     */
    @SuppressWarnings("unchecked")
    public static <PROP, OBJ> Map<PROP, OBJ> makeMapByProperty(List<? extends OBJ> list, String propertyName) {
        Map<PROP, OBJ> map = new HashMap<>();
        if (CollectionUtils.isEmpty(list)) {
            return map;
        }
        Class<?> clz = list.get(0).getClass();
        try {
            Field field = clz.getDeclaredField(propertyName);
            field.setAccessible(true);
            for (OBJ item : list) {
                PROP value;
                try {
                    value = (PROP)field.get(item);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                if (value == null) {
                    continue;
                }
                if (map.containsKey(value)) {
                    throw new IllegalArgumentException("list property is not unique");
                }
                map.put(value, item);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 获取List里面对象的某个属性形成一个集合, 形成的集合与原集合顺序相同
     *
     * @param list         list对象
     * @param propertyName 对象属性名称
     * @return 对象属性列表
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> getByPropertyValue(List<?> list, String propertyName) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<T>();
        }
        List<T> retList = new ArrayList<T>(list.size());
        Set<T> set = new LinkedHashSet<T>(list.size());
        Class<?> clz = list.get(0).getClass();
        try {
            Field field = clz.getDeclaredField(propertyName);
            field.setAccessible(true);
            for (Object item : list) {
                Object value;
                value = field.get(item);
                if (value == null) {
                    continue;
                }
                set.add((T) value);
            }
            retList.addAll(set);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retList;
    }

    /**
     * 获取不会为null的list集合,若list为null会返回一个新的空集合, 否则直接返回原集合
     *
     * @param collection list集合
     * @return 非null list集合
     */
    public static <T> List<T> getNonNullList(List<T> collection) {
        if (collection == null) {
            return Collections.emptyList();
        }
        return collection;
    }

    /**
     * 生成一个长度为length的0-9随机数如5231410
     *
     * @param length 长度
     * @return 随机数
     */
    public static String generateRandomNumKeys(Integer length) {
        if (length > 512) {
            length = 512;
        } else if (length < 0) {
            length = 0;
        }
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    private void test() {
        List<Person> list = new ArrayList<Person>() {{
            add(new Person("bbb", 2));
            add(new Person("aaa", 1));
            add(new Person("ccc", 3));
        }};
        System.out.println(getByPropertyValue(list, "name"));
    }

    class Person {
        String name;
        Integer age;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                   "name='" + name + '\'' +
                   ", age=" + age +
                   '}';
        }
    }

    public static void main(String[] args) {
        new CommonUtils().test();
    }
}
