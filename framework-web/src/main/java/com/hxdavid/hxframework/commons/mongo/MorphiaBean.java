package com.hxdavid.hxframework.commons.mongo;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class MorphiaBean extends Morphia {

    private MongoClient mongo    = null;

    private String    dbName   = null;
    private Datastore ds       = null;

    private String    username = null;
    private String    password = null;

    public MorphiaBean() {
        super();
    }

    public void setMapPackage(String packageName) {
        super.mapPackage(packageName, true);
    }

    public void setMongo(MongoClient mongo) {
        this.mongo = mongo;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public synchronized Datastore getDataStore() {
        if (this.mongo == null || this.dbName == null) {
            return null;
        }
        if (this.ds == null) {
            if (username == null || password == null) {
                this.ds = createDatastore(mongo, dbName);
            } else {
                this.ds = createDatastore(mongo, dbName);
            }
            return ds;
        } else {
            return this.ds;
        }
    }

    public MongoClient getMongo() {
        return mongo;
    }

    public String getDbName() {
        return dbName;
    }

    public DB getDB() {
        if (dbName == null || dbName.trim().equalsIgnoreCase("")) {
            return null;
        }
        return mongo.getDB(dbName);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
