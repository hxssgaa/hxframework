package com.hxdavid.hxframework.sampleweb.bean;

import com.hxdavid.hxframework.sampleweb.service.BlackListService;
import com.hxdavid.hxframework.utils.annotation.RequestParam;
import com.hxdavid.hxframework.utils.exception.CommonFrontNotifiableException;
import com.hxdavid.hxframework.utils.lock.ZookeeperLock;
import com.hxdavid.hxframework.utils.lock.ZookeeperLockContext;
import net.spy.memcached.MemcachedClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author hzhuangxin3@corp.netease.com, Huang Xin
 * @date 2016-06-12 15:02
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

//    @Resource
//    private MemcachedClient      memcachedClient;
//    @Resource
//    private ZookeeperLockContext zookeeperLockContext;
    @Resource
    private BlackListService blackListService;

    private static volatile Integer count = 0;

    @RequestMapping(value = "/testExp", method = { RequestMethod.GET })
    public
    @ResponseBody
    String testExp(@RequestParam(value = "code", required = false) Integer code, HttpServletRequest request) {
        if (code == null) {
            return "hello, world";
        }
        if (code == 0) {
            throw new CommonFrontNotifiableException("前端文案提示");
        } else if (code == 1) {
            throw new NullPointerException("空指针系统异常");
        }
        return "hello, world";
    }

//    @RequestMapping("/sayHello")
//    public
//    @ResponseBody
//    String sayHello() {
//        ZookeeperLock lock = zookeeperLockContext.getLock("key1");
//        boolean flag = false;
//        try {
//            flag = lock.lock(5, TimeUnit.SECONDS);
//            System.out.println("Doing heavy stuff");
//            memcachedClient.add("key", 3600 * 24, "hello, world 42");
//            Thread.sleep(1000);
//            System.out.println("Heavy stuff done");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            if (flag) {
//                lock.unlock();
//            }
//        }
//        return "hello, world";
//    }
//
//    @RequestMapping("/getHello")
//    public
//    @ResponseBody
//    String getHello() {
//        return (String) memcachedClient.get("key");
//    }
//
//    @RequestMapping("/testZk")
//    public
//    @ResponseBody
//    String testZk() throws Exception{
//        final CountDownLatch down = new CountDownLatch(1);
//        final ZookeeperLock lock = zookeeperLockContext.getLock("curator_recipes_lock_path");
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                boolean flag = false;
//                try {
//                    down.await();
//                    flag = lock.lock(5, TimeUnit.SECONDS);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
//                String orderNo = sdf.format(new Date());
//                System.err.println("生成的订单号(" + count++ + ")是:" + orderNo);
//                try {
//                    if (flag) {
//                        lock.unlock();
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }).start();
//        }
//        down.countDown();
//        return "test done";
//    }

    @RequestMapping(value = "/testClientGetResponse", method = { RequestMethod.GET })
    public
    @ResponseBody
    String testClientGetResponse(@RequestParam(value = "id", required = true) Long id) {
        return blackListService.testDoingSomething(id);
    }

    public static void main(String[] args) {
        Arrays.asList(1, 2, 3, 4, 5).forEach(e -> {
            System.out.println(e);
        });
    }

}
