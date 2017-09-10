package com.hxdavid.hxframework.utils.lock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;

/**
 * @author hzhuangxin3@corp.netease.com, Huang Xin
 * @date 2016-06-13 14:34
 */
public class ZookeeperLockContext implements InitializingBean {

    private static final String LOCK_ROOT = "/locks_v2";

    private String             namespace;
    private int                concurrency;                                                   // 1000
    private HashCalculator     hashCalculator = new HashCalculator(concurrency);
    private CuratorFramework   client;

    private static final Log log = LogFactory.getLog(ZookeeperLockContext.class);

    public void init() throws Exception {

        if (concurrency < 0 || StringUtils.isEmpty(namespace)) {

            String errorInfo = String.format("illegal arguments for init. concurrency=%d;namespace=%s;",
                                             concurrency, namespace);
            log.error(errorInfo);
            throw new Exception(errorInfo);
        }

        hashCalculator = new HashCalculator(concurrency);

        try {
            initInner();
            if (log.isInfoEnabled()) {
                log.info("ZookeeperLockContext.init successfully!");
            }

        } catch (Exception e) {
            log.error("init error", e);
            throw new Exception("can not init ZookeeperLockContext!", e);
        }

    }

    private StringBuilder getLockPathStaticPart() {
        StringBuilder sb = new StringBuilder();
        sb.append("/").append(getNamespace()).append(LOCK_ROOT);
        return sb;
    }

    private StringBuilder getLockPathWithAll(String key) {
        if (StringUtils.isEmpty(key)) {
            key = "";
        }
        int keyHash = hashCalculator.getIndexOfValue(key.hashCode());
        return getLockPathStaticPart().append("/").append(keyHash);
    }

    private void initInner() throws Exception {

        client = ZookeeperUtils.getZooKeeper();

        // 创建根节点
        checkAndCreateNode(client, getLockPathStaticPart().toString(), CreateMode.PERSISTENT);

    }

    private ZookeeperLock getLockInner(String key) {

        String path = getLockPathWithAll(key).toString();
        try {
            return new ZookeeperLock(client, path);
        } catch (Exception e) {
            throw new RuntimeException("this should not happened", e);
        }

    }

    public static void checkAndCreateNode(CuratorFramework zk, String path, CreateMode mode) throws Exception {
        Stat stat = zk.checkExists().forPath(path);
        if (stat == null) {
            try {
                String node = zk
                        .create()
                        .creatingParentsIfNeeded()
                        .withMode(mode)
                        .forPath(path, new byte[0]);
                if (log.isDebugEnabled()) {
                    log.debug(node + " is created");
                }
            } catch (KeeperException.NodeExistsException e) {
                log.warn("create error 4 exist already ,but continue for path: " + path, e);
            }

        }
    }

    public ZookeeperLock getLock(String key) {
        try {
            return getLockInner(key);
        } catch (Exception e) {
            log.error("getLock error,key=%s", e);
            throw new RuntimeException("can not getLock!", e);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        try {
            init();
        } catch (Exception e) {
            log.error("init zookeeper context error", e);
            throw new BeansException("init zookeeper context error", e) {

            };
        }

    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public int getConcurrency() {
        return concurrency;
    }

    public void setConcurrency(int concurrency) {
        this.concurrency = concurrency;
    }

}

