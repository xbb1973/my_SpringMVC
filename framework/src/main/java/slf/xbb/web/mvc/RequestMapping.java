package slf.xbb.web.mvc;

import java.lang.annotation.*;

/**
 * @author ：xbb
 * @date ：Created in 2020/5/31 12:21 下午
 * @description：注解
 * @modifiedBy：
 * @version:
 */
@Documented
// 保留到运行期
@Retention(RetentionPolicy.RUNTIME)
// 作用目标为方法
@Target(ElementType.METHOD)
public @interface RequestMapping {
    String value();
}
