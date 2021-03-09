package it.academy.web.controller;

import it.academy.pojos.Employee;
import it.academy.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/page_1")
    public String home(Model model) {
        model.addAttribute("employeeList", employeeService.findAllEmployee());
        return "page_1";
    }

    @RequestMapping(
            value = {"/page_2"},
            method = RequestMethod.GET)
    public String pageTwo(ModelMap model) {
        return "page_2";
    }

    @PostMapping("/page_2/add")
    public String addNewEmployee(@ModelAttribute Employee employee){
        employeeService.addNewEmployee(employee);
        return "redirect:/page_1";
    }
}