package slf.xbb.web.servlet;

import slf.xbb.web.handler.HandlerManager;
import slf.xbb.web.handler.MappingHandler;

import javax.servlet.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author ：xbb
 * @date ：Created in 2020/5/31 12:12 下午
 * @description：Servlet分发
 * @modifiedBy：
 * @version:
 */
public class DispatcherServlet implements Servlet {
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
        for (MappingHandler mappingHandler : HandlerManager.mappingHandlerList) {
            try {
                if (mappingHandler.handle(req, res)) {
                    return;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        // resp写入结果
        res.getWriter().println("Hello World Dispatcher Test");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
