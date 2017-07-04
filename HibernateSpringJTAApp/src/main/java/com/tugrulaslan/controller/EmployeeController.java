package com.tugrulaslan.controller;

import com.tugrulaslan.entity.Employee;
import com.tugrulaslan.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Tugrul on 7/4/2017.
 */
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(method = RequestMethod.POST)
    public String add(@ModelAttribute(value = "newEmployee") Employee employee, BindingResult result, Model model) {
        if (result.hasErrors())
            return "employee";

        employeeRepository.saveEmployee(employee);
        return "redirect:/employee";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("list", employeeRepository.getAllEmployee());
        model.addAttribute("newEmployee", new Employee());
        return "employee";
    }
}