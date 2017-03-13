package com.cjzheng.test.servlet;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.InvocationContext;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;
import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by zhengchaojie on 2017/3/13/0013.
 */
public class TestHelloWorld extends TestCase {
    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testHelloWorld() {
        try {
            // 创建Servlet的运行环境
            ServletRunner servletRunner = new ServletRunner();
            // 向环境中注册Servlet
            servletRunner.registerServlet("/test/hello", HelloWorldServlet.class.getName());
            // 创建访问Servlet的客户端
            ServletUnitClient servletUnitClient = servletRunner.newClient();
            // 发送请求
            WebRequest request = new GetMethodWebRequest("http://localhost:8080/test/hello");
            InvocationContext invocationContext = servletUnitClient.newInvocation(request);
            HelloWorldServlet helloWorldServlet = (HelloWorldServlet) invocationContext.getServlet();
            // 测试servlet的某个方法
            Assert.assertTrue(helloWorldServlet.authenticate());
            // 获得模拟服务器的信息
            WebResponse response = servletUnitClient.getResponse(request);
            System.out.println(response.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
