package com.hxdavid.hxframework.commons.mongo;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.List;

/**
 * Created by yangzhou on 15-2-9.
 */
public interface BaseMongoService<T,S> {

    T getById(S id);

    /**
     * save is not only add, it also update all columns when exists( by _id)
     * @param object
     */
    void save(T object);

    void update(Query<T> selectQuery, UpdateOperations<T> updateOperations);

    void remove(S id);

    void removeAll(List<S> ids);

    Datastore getDataStore();

    /**
     * 通过复杂条件查询
     * properties 中的string参考
     * <ul>
     *   <li>{@code filter("yearsOfOperation >", 5)}</li>
     *   <li>{@code filter("rooms.maxBeds >=", 2)}</li>
     *   <li>{@code filter("rooms.bathrooms exists", 1)}</li>
     *   <li>{@code filter("stars in", new Long[]{3, 4}) //3 and 4 stars (midrange?)}</li>
     *   <li>{@code filter("quantity mod", new Long[]{4, 0}) // customers ordered in packs of 4)}</li>
     *   <li>{@code filter("age >=", age)}</li>
     *   <li>{@code filter("age =", age)}</li>
     *   <li>{@code filter("age", age)} (if no operator, = is assumed)</li>
     *   <li>{@code filter("age !=", age)}</li>
     *   <li>{@code filter("age in", ageList)}</li>
     *   <li>{@code filter("customers.loyaltyYears in", yearsList)}</li>
     * </ul>
     * <p/>
     * <p>You can filter on id properties <strong>if</strong> this query is restricted to a Class<T>.
     */
    List<T> getByCondition(List<String> properties, Object... parameters);

    /**
     * 通过复杂条件查询
     * properties 中的string参考
     * <ul>
     *   <li>{@code filter("yearsOfOperation >", 5)}</li>
     *   <li>{@code filter("rooms.maxBeds >=", 2)}</li>
     *   <li>{@code filter("rooms.bathrooms exists", 1)}</li>
     *   <li>{@code filter("stars in", new Long[]{3, 4}) //3 and 4 stars (midrange?)}</li>
     *   <li>{@code filter("quantity mod", new Long[]{4, 0}) // customers ordered in packs of 4)}</li>
     *   <li>{@code filter("age >=", age)}</li>
     *   <li>{@code filter("age =", age)}</li>
     *   <li>{@code filter("age", age)} (if no operator, = is assumed)</li>
     *   <li>{@code filter("age !=", age)}</li>
     *   <li>{@code filter("age in", ageList)}</li>
     *   <li>{@code filter("customers.loyaltyYears in", yearsList)}</li>
     * </ul>
     * <p/>
     * <p>You can filter on id properties <strong>if</strong> this query is restricted to a Class<T>.
     * @param skip 跳过指定个元素
     * @param size 返回指定个元素
     */
    List<T> getByConditionForPagination(int skip, int size, List<String> properties, Object... parameters);

    /**
     * 通过复杂条件查询个数
     * properties 中的string参考
     * <ul>
     *   <li>{@code filter("yearsOfOperation >", 5)}</li>
     *   <li>{@code filter("rooms.maxBeds >=", 2)}</li>
     *   <li>{@code filter("rooms.bathrooms exists", 1)}</li>
     *   <li>{@code filter("stars in", new Long[]{3, 4}) //3 and 4 stars (midrange?)}</li>
     *   <li>{@code filter("quantity mod", new Long[]{4, 0}) // customers ordered in packs of 4)}</li>
     *   <li>{@code filter("age >=", age)}</li>
     *   <li>{@code filter("age =", age)}</li>
     *   <li>{@code filter("age", age)} (if no operator, = is assumed)</li>
     *   <li>{@code filter("age !=", age)}</li>
     *   <li>{@code filter("age in", ageList)}</li>
     *   <li>{@code filter("customers.loyaltyYears in", yearsList)}</li>
     * </ul>
     * <p/>
     * <p>You can filter on id properties <strong>if</strong> this query is restricted to a Class<T>.
     */
    long countByCondition(List<String> properties, Object... parameters);
    /**
     * 通过复杂条件查询一个元素
     * properties 中的string参考
     * <ul>
     *   <li>{@code filter("yearsOfOperation >", 5)}</li>
     *   <li>{@code filter("rooms.maxBeds >=", 2)}</li>
     *   <li>{@code filter("rooms.bathrooms exists", 1)}</li>
     *   <li>{@code filter("stars in", new Long[]{3, 4}) //3 and 4 stars (midrange?)}</li>
     *   <li>{@code filter("quantity mod", new Long[]{4, 0}) // customers ordered in packs of 4)}</li>
     *   <li>{@code filter("age >=", age)}</li>
     *   <li>{@code filter("age =", age)}</li>
     *   <li>{@code filter("age", age)} (if no operator, = is assumed)</li>
     *   <li>{@code filter("age !=", age)}</li>
     *   <li>{@code filter("age in", ageList)}</li>
     *   <li>{@code filter("customers.loyaltyYears in", yearsList)}</li>
     * </ul>
     * <p/>
     * <p>You can filter on id properties <strong>if</strong> this query is restricted to a Class<T>.
     */
    T getOneByCondition(List<String> properties, Object... parameters);
}
