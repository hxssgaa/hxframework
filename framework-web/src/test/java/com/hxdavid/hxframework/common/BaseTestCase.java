package com.hxdavid.hxframework.common;

import org.apache.commons.lang.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author hzhuangxin3@corp.netease.com, Huang Xin
 * @date 2016-06-14 14:01
 */
@Test
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:/applicationContext-common.xml",
        "classpath:/applicationContext-component-scan.xml",
        "classpath:/applicationContext-dao-base.xml",
        "classpath:/applicationContext-aop-base.xml",
        "classpath:/conf/hxframework-servlet.xml",
        "classpath:/applicationContext-dubbo-consumer.xml"})
public class BaseTestCase extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext context;

    protected MockMvc mockMvc;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(context);
        this.mockMvc = builder.build();
    }

    /**
     * Mocks the GET request.
     */
    protected String getMock(String url, Object[] params) throws Exception {
        // 2. 构造GET请求
        MockHttpServletRequestBuilder requestBuilder = getGetRequestBuilder(url, params);
        return getResponseString(this.jsonRequestMock(requestBuilder));
    }

    private MockHttpServletRequestBuilder getGetRequestBuilder(String url, Object[] params) {
        return MockMvcRequestBuilders.get(url, params);
    }

    /**
     * 根据返回的结果ResultActions得到返回字符串
     *
     * @param resultActions HTTP返回的结果
     * @return HTTP返回的字符串
     */
    private String getResponseString(ResultActions resultActions) {
        MockHttpServletResponse response = resultActions.andReturn().getResponse();
        try {
            return response.getContentAsString();
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("结果的编码不被支持");
        }
    }

    private ResultActions jsonRequestMock(MockHttpServletRequestBuilder requestBuilder) throws Exception {
        // 设置HTTP请求属性
        requestBuilder.contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding(CharEncoding.UTF_8)
        ;

        // 定义期望响应行为
        return this.mockMvc.perform(requestBuilder)
                .andDo(print()) // 打印整个请求与响应细节
                .andExpect(status().isOk())
                ;
    }

}
