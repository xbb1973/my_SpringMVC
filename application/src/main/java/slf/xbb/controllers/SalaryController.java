package slf.xbb.controllers;

import slf.xbb.beans.AutoWired;
import slf.xbb.service.SalaryService;
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

    @AutoWired
    SalaryService salaryService;

    @RequestMapping("/test1")
    public String getSalary(@RequestParam("name") String name,
                            @RequestParam("exp") String exp) {

        return name + exp;
    }
    @RequestMapping("/test2")
    public Integer test2() {
        return salaryService.getSalary();
    }
}
