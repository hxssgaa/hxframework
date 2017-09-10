package com.hxdavid.hxframework.commons.mongo;

import org.apache.commons.lang.Validate;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by yangzhou on 15-2-9.
 * Modified By hzhuangxin3 on 16-6-14
 */
public class BaseMongoServiceImpl<T, S> implements BaseMongoService<T, S> {

    private MorphiaBean baseMorphia;
    private Class<T>    entityClass;
    Datastore ds;

    public MorphiaBean getBaseMorphia() {
        return baseMorphia;
    }

    @SuppressWarnings("unchecked")
    public void setBaseMorphia(MorphiaBean baseMorphia) {
        this.baseMorphia = baseMorphia;
        this.entityClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public T getById(S id) {
        Datastore ds = getDataStore();
        return ds.get(entityClass, id);
    }

    @Override
    public void save(T object) {
        Datastore ds = getDataStore();
        ds.save(object);
    }

    @Override
    public void update(Query<T> selectQuery, UpdateOperations<T> updateOperations) {
        Datastore ds = getDataStore();
        ds.update(selectQuery, updateOperations);
    }

    @Override
    public void remove(S id) {
        Datastore ds = getDataStore();
        ds.delete(ds.find(entityClass, "_id  ", id));
    }

    @Override
    public void removeAll(List<S> ids) {
        Datastore ds = getDataStore();
        Query<T> query = ds.createQuery(entityClass).filter("id in", ids);
        ds.delete(query);
    }

    @Override
    public Datastore getDataStore() {
        return baseMorphia.getDataStore();
    }

    @Override
    public List<T> getByCondition(List<String> properties, Object... parameters) {
        Validate.notEmpty(properties);
        Validate.notEmpty(parameters);
        Validate.isTrue(properties.size() == parameters.length);

        Datastore ds = getDataStore();
        Query<T> query = ds.createQuery(entityClass);
        for (int i = 0; i < properties.size(); i++) {
            query.filter(properties.get(i), parameters[i]);
        }
        return query.asList();
    }

    @Override
    public List<T> getByConditionForPagination(int skip, int size, List<String> properties, Object... parameters) {
        Validate.notEmpty(properties);
        Validate.notEmpty(parameters);
        Validate.isTrue(properties.size() == parameters.length);

        Datastore ds = getDataStore();
        Query<T> query = ds.createQuery(entityClass);
        for (int i = 0; i < properties.size(); i++) {
            query.filter(properties.get(i), parameters[i]);
        }
        query.offset(skip);
        query.limit(size);
        return query.asList();
    }

    @Override
    public long countByCondition(List<String> properties, Object... parameters) {
        Validate.notEmpty(properties);
        Validate.notEmpty(parameters);
        Validate.isTrue(properties.size() == parameters.length);

        Datastore ds = getDataStore();
        Query<T> query = ds.createQuery(entityClass);
        for (int i = 0; i < properties.size(); i++) {
            query.filter(properties.get(i), parameters[i]);
        }
        return query.countAll();
    }

    @Override
    public T getOneByCondition(List<String> properties, Object... parameters) {
        Validate.notEmpty(properties);
        Validate.notEmpty(parameters);
        Validate.isTrue(properties.size() == parameters.length);

        Datastore ds = getDataStore();
        Query<T> query = ds.createQuery(entityClass);
        for (int i = 0; i < properties.size(); i++) {
            query.filter(properties.get(i), parameters[i]);
        }
        return query.get();
    }
}
