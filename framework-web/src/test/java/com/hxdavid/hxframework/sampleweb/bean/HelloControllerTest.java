package com.hxdavid.hxframework.sampleweb.bean;

import com.hxdavid.hxframework.common.BaseTestCase;
import com.hxdavid.hxframework.sampleweb.constants.BlockStuffTypeEnum;
import com.hxdavid.hxframework.sampleweb.dto.BlockStuffDto;
import com.hxdavid.hxframework.sampleweb.service.BlackListService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * @author hzhuangxin3@corp.netease.com, Huang Xin
 * @date 2016-06-15 14:34
 */
public class HelloControllerTest extends BaseTestCase{

    @Mock
    private BlackListService blackListService;

    @Resource
    @InjectMocks
    private HelloController helloController;

    @BeforeClass
    public void setup() {
        super.setup();
        MockitoAnnotations.initMocks(this);
    }

    @Test(dataProvider = "doingSomethingParam")
    public void testDoingSomething(Long id) throws Exception{
        when(blackListService.testDoingSomething(anyLong())).thenReturn("wow, this is amazing");
        String resp = getMock("/hello/testClientGetResponse?id={id}", new Object[]{id});
        System.out.println(resp);
    }

    @DataProvider(name = "doingSomethingParam")
    protected static Object[][] doingSomethingParam() {
        return new Object[][] {
                { 123L }
        };
    }
}
