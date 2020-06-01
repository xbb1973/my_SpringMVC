package slf.xbb.web.mvc;

import java.lang.annotation.*;

/**
 * @author ：xbb
 * @date ：Created in 2020/5/31 12:19 下午
 * @description：Controller注解
 * @modifiedBy：
 * @version:
 */
@Documented
// 保留到运行期
@Retention(RetentionPolicy.RUNTIME)
// 作用目标为类
@Target(ElementType.TYPE)
public @interface Controller {
}
