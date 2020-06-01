package slf.xbb.web.server;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import slf.xbb.web.servlet.DispatcherServlet;
import slf.xbb.web.servlet.TestServlet;

/**
 * @author ：xbb
 * @date ：Created in 2020/5/31 10:56 上午
 * @description：Tomcat服务，仿Spring集成Tomcat服务器
 * @modifiedBy：
 * @version:
 */
public class TomcatServer {
    private Tomcat tomcat;
    String[] args;

    public TomcatServer(String[] args) {
        this.args = args;
    }

    public void startServer() throws LifecycleException {
        tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.start();

        // 创建Context容器
        Context context = new StandardContext();
        context.setPath("");
        context.addLifecycleListener(new Tomcat.FixContextListener());
        DispatcherServlet dispacherServlet = new DispatcherServlet();
        // 注册Servlet
        Tomcat.addServlet(context, "dispacherServlet", dispacherServlet)
                .setAsyncSupported(true);
        context.addServletMappingDecoded("/test", "dispacherServlet");
        tomcat.getHost().addChild(context);

        Thread awaitThread = new Thread(() -> {
            TomcatServer.this.tomcat.getServer().await();
        }, "tomcat_await_thread");
        awaitThread.setDaemon(false);
        awaitThread.start();
    }
}
