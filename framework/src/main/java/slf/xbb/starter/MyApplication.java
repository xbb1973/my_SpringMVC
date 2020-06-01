package slf.xbb.starter;

import org.apache.catalina.LifecycleException;
import slf.xbb.beans.BeanFactory;
import slf.xbb.core.ClassScanner;
import slf.xbb.web.handler.HandlerManager;
import slf.xbb.web.server.TomcatServer;

import java.io.IOException;
import java.util.List;

/**
 * @author ：xbb
 * @date ：Created in 2020/5/31 9:48 上午
 * @description：启动类
 * @modifiedBy：
 * @version:
 */
public class MyApplication {
    public static void run(Class<?> clazz, String[] args) {
        System.out.println("Hellow MyApplication");
        TomcatServer tomcatServer = new TomcatServer(args);
        try {
            tomcatServer.startServer();
            List<Class<?>> classList = ClassScanner.scanClasses(clazz.getPackage().getName());
            BeanFactory.initBean(classList);
            HandlerManager.resolveMappingHandler(classList);
            classList.forEach(System.out::println);
        } catch (LifecycleException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
