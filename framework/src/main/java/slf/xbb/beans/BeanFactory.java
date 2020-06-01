package slf.xbb.beans;

import slf.xbb.web.mvc.Controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ：xbb
 * @date ：Created in 2020/6/1 1:12 下午
 * @description：Bean工厂
 * @modifiedBy：
 * @version:
 */
public class BeanFactory {
    private static Map<Class<?>, Object> classToBeanMap = new ConcurrentHashMap<>();

    public static Object getBean(Class<?> clazz) {
        return classToBeanMap.get(clazz);
    }

    public static void initBean(List<Class<?>> classList) throws Exception {
        List<Class<?>> toCreate = new ArrayList<>(classList);
        while (toCreate.size() != 0) {
            int remainSize = toCreate.size();
            for (int i = 0; i < toCreate.size(); i++) {
                if (finishCreate(toCreate.get(i))) {
                    toCreate.remove(i); // 这里有线程不安全问题
                }
            }
            if (toCreate.size() == remainSize){
                throw new Exception("Cycle dependency");
            }
        }
    }

    private static boolean finishCreate(Class<?> clazz) throws IllegalAccessException, InstantiationException {
        if (!clazz.isAnnotationPresent(Bean.class)
                && !clazz.isAnnotationPresent(Controller.class)) {
            return true;
        }
        Object bean = clazz.newInstance();
        for (Field filed : clazz.getDeclaredFields()){
            if (filed.isAnnotationPresent(AutoWired.class)) {
                Class<?> filedType = filed.getType();
                Object reliantBean = BeanFactory.getBean(filedType);
                if (reliantBean == null) {
                    return false;
                }
                filed.setAccessible(true);
                filed.set(bean, reliantBean);
            }
        }
        classToBeanMap.put(clazz, bean);
        return true;
    }
}
