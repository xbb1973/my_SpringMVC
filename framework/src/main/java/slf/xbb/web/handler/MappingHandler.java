package slf.xbb.web.handler;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author ：xbb
 * @date ：Created in 2020/5/31 2:48 下午
 * @description：
 * @modifiedBy：
 * @version:
 */
public class MappingHandler {
    private String uri;
    private Method method;
    private Class<?> controller;
    private String[] args;

    MappingHandler(String uri, Method method, Class<?> clazz, String[] args) {
        this.uri = uri;
        this.method = method;
        this.controller = clazz;
        this.args = args;
    }

    public boolean handle(ServletRequest req, ServletResponse res) throws IllegalAccessException, InstantiationException, InvocationTargetException, IOException {
        String requestUri = ((HttpServletRequest) req).getRequestURI();
        if (!uri.equals(requestUri)) {
            return false;
        }
        Object[] parameters = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            parameters[i] = req.getParameter(args[i]);
        }
        Object ctrlInstance = controller.newInstance();
        Object result = method.invoke(ctrlInstance, parameters);
        res.getWriter().println(result.toString());
        return true;
    }
}
