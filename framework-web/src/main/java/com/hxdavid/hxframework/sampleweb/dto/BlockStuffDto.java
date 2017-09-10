package com.hxdavid.hxframework.sampleweb.dto;

import org.mongodb.morphia.annotations.*;
import org.mongodb.morphia.utils.IndexDirection;

import java.io.Serializable;

/**
 * @author hzhuangxin3@corp.netease.com, Huang Xin
 * @date 2016-06-15 13:56
 */
@Entity(value = "block_stuff", noClassnameStored = true)
@Indexes({ @Index("type") })
public class BlockStuffDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @Id
    private String  id;
    private String  name;
    private String  abbreviatedName;
    @Indexed(value = IndexDirection.ASC, name = "idxType")
    private Integer type;//1--people, 2--game, BlockStuffTypeEnum
    private String  typeName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviatedName() {
        return abbreviatedName;
    }

    public void setAbbreviatedName(String abbreviatedName) {
        this.abbreviatedName = abbreviatedName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "BlockStuffDto{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", abbreviatedName='" + abbreviatedName + '\'' +
               ", type=" + type +
               ", typeName='" + typeName + '\'' +
               '}';
    }
}
