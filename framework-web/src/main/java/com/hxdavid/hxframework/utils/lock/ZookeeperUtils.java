package com.hxdavid.hxframework.utils.lock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.KeeperException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import java.io.IOException;

/**
 * @author hzhuangxin3@corp.netease.com, Huang Xin
 * @date 2016-06-13 14:13
 *
 * ZookeeperUtils为提供CuratorFramework的工厂Bean.
 * 它实现了BeanFactoryAware, 因此可以拿到BeanFactory对象从而拿到ZookeeperUtils对象
 *
 * 可能觉得奇怪的是ZookeeperUtils其实就是Spring的一个Bean, 那么为什么要用BeanFactoryAware呢?
 * 是因为这个类作为一个工厂模式的类来使用, 实例只能初始化一次. 否则的话ZookeeperUtils实例就有可能被创建多次,
 * 这是我们不希望的, 因为CuratorFramework和ZookeeperUtils都只能被创建一次. 这是一个典型的工厂Bean的使用.
 * 保证了该Bean不会被创建多次.
 */
public class ZookeeperUtils implements BeanFactoryAware {

    private static ZookeeperUtils S_INSTANCE = null;

    private static CuratorFramework S_ZOOKEEPER = null;

    // "grey-dell-v1:2181,grey-dell-v2:2181,grey-dell-v3:2181";
    // "study10.server.163.org:2181,study19.server.163.org:2181,study26.server.163.org:2181"
    private String connectStr        = null;
    private int    sessionTimeout    = 60000;
    private int    baseSleepTimeMs   = 60000;
    private int    maxTryCount       = 3;
    private int    connectionTimeout = 15000;

    private ZookeeperUtils() {}

    private static final Log log = LogFactory.getLog(ZookeeperUtils.class);

    public static CuratorFramework getZooKeeper() throws IOException, KeeperException, InterruptedException {
        if (S_ZOOKEEPER != null) {
            return S_ZOOKEEPER;
        }

        synchronized (ZookeeperUtils.class) {
            if (S_ZOOKEEPER != null) {
                return S_ZOOKEEPER;
            }

            CuratorFramework client = CuratorFrameworkFactory
                    .builder()
                    .connectString(S_INSTANCE.connectStr)
                    .sessionTimeoutMs(S_INSTANCE.sessionTimeout)
                    .connectionTimeoutMs(S_INSTANCE.connectionTimeout)
                    .retryPolicy(new ExponentialBackoffRetry(
                            S_INSTANCE.baseSleepTimeMs,
                            S_INSTANCE.maxTryCount))
                    .build();

            if (client != null) {
                client.start();
                S_ZOOKEEPER = client;
            } else {
                throw new RuntimeException("zookeeper create fail.connectUrl:" + S_INSTANCE.connectStr);
            }

            log.info("zookeeper create successfully .connectUrl:" + S_INSTANCE.connectStr);
            return S_ZOOKEEPER;

        }

    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        S_INSTANCE = beanFactory.getBean("zookeeperUtils", ZookeeperUtils.class);
    }

    public String getConnectStr() {
        return connectStr;
    }

    public void setConnectStr(String connectStr) {
        this.connectStr = connectStr;
    }

    public int getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(int sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public int getMaxTryCount() {
        return maxTryCount;
    }

    public void setMaxTryCount(int maxTryCount) {
        this.maxTryCount = maxTryCount;
    }

    public int getBaseSleepTimeMs() {
        return baseSleepTimeMs;
    }

    public void setBaseSleepTimeMs(int baseSleepTimeMs) {
        this.baseSleepTimeMs = baseSleepTimeMs;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

}
