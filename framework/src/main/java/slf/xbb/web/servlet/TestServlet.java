package slf.xbb.web.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author ：xbb
 * @date ：Created in 2020/5/31 11:21 上午
 * @description：测试Servlet
 * @modifiedBy：
 * @version:
 */
public class TestServlet implements Servlet {
    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        // req读取参数


        // resp写入结果
        res.getWriter().println("Hello World Test");

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
