package com.hxdavid.hxframework.sampleweb.constants;

/**
 * @author hzhuangxin3@corp.netease.com, Huang Xin
 * @date 2016-06-15 14:05
 */
public enum BlockStuffTypeEnum {
    TYPE_PERSON(1, "person"), TYPE_GAME(2, "game");

    private Integer value;
    private String  name;

    BlockStuffTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
