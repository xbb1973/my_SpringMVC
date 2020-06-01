package slf.xbb.web.handler;

import slf.xbb.web.mvc.Controller;
import slf.xbb.web.mvc.RequestMapping;
import slf.xbb.web.mvc.RequestParam;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：xbb
 * @date ：Created in 2020/5/31 2:51 下午
 * @description：
 * @modifiedBy：
 * @version:
 */
public class HandlerManager {
    public static List<MappingHandler> mappingHandlerList = new ArrayList<>();

    /**
     * 获取含有注解Controller的类
     * @param classList
     */
    public static void resolveMappingHandler(List<Class<?>> classList) {
        for (Class<?> aClass : classList) {
            if (aClass.isAnnotationPresent(Controller.class)) {
                parseHandlerFromController(aClass);
            }
        }
    }

    /**
     * 从含有注解Controller的类中获取有注解RequestMapping的方法的value值(uri)和参数
     * @param clazz
     */
    private static void parseHandlerFromController(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (!method.isAnnotationPresent(RequestMapping.class)) {
                continue;
            }
            String uri = method.getDeclaredAnnotation(RequestMapping.class).value();
            List<String> parameterList = new ArrayList<>();
            for (Parameter parameter : method.getParameters()) {
                if (parameter.isAnnotationPresent(RequestParam.class)) {
                    parameterList.add(parameter.getDeclaredAnnotation(RequestParam.class).value());
                }
            }
            String[] params = parameterList.toArray(new String[parameterList.size()]);
            MappingHandler mappingHandler = new MappingHandler(uri, method, clazz, params);
            HandlerManager.mappingHandlerList.add(mappingHandler);
        }
    }
}
