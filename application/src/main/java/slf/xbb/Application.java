package slf.xbb;
import slf.xbb.starter.MyApplication;
/**
 * @author ：xbb
 * @date ：Created in 2020/5/31 9:35 上午
 * @description：启动类
 * @modifiedBy：
 * @version:
 */
public class Application {
    public static void main(String[] args) {
        System.out.println("Hello world");
        MyApplication.run(Application.class, args);
    }
}
