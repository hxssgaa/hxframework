package com.hxdavid.hxframework.utils.lock;

import com.google.common.base.Throwables;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.util.concurrent.TimeUnit;

/**
 * @author hzhuangxin3@corp.netease.com, Huang Xin
 * @date 2016-06-13 14:55
 */
public class ZookeeperLock {

    private InterProcessLock lock;
    private String           path;

    private static final Log log = LogFactory.getLog(ZookeeperLock.class);

    public ZookeeperLock(CuratorFramework client, String path) {
        this.lock = new InterProcessMutex(client, path);
        this.path = path;
    }


    public void lock() {
        try {
            lock.acquire();
            if (log.isDebugEnabled()) {
                log.debug(path + " is created currently ");
            }
        } catch (Exception e) {
            Throwables.propagate(e);
        }
    }

    public boolean lock(long l, TimeUnit timeUnit) {
        boolean res = false;
        try {
            res = lock.acquire(l, timeUnit);
            if (log.isDebugEnabled()) {
                log.debug(path + " is created currently ");
            }
        } catch (Exception e) {
            Throwables.propagate(e);
        }
        return res;
    }

    public void unlock() {
        try {
            lock.release();
            if (log.isDebugEnabled()) {
                log.debug(path + " is release currently ");
            }
        } catch (Exception e) {
            Throwables.propagate(e);
        }
    }
}
