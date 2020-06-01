package slf.xbb.controllers;

import slf.xbb.web.mvc.Controller;
import slf.xbb.web.mvc.RequestMapping;
import slf.xbb.web.mvc.RequestParam;

/**
 * @author ：xbb
 * @date ：Created in 2020/5/31 12:25 下午
 * @description：SalaryController
 * @modifiedBy：
 * @version:
 */
@Controller
public class SalaryController {
    @RequestMapping("/salary/get")
    public String getSalary(@RequestParam("name") String name,
                             @RequestParam("exp") String exp) {

        return name + exp;
    }
}
