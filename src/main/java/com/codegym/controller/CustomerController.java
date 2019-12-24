package com.codegym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("customer/")
public class CustomerController {

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ModelAndView listCustomers(){

        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("message","Danh sach khach hang");

        return modelAndView;
    }
}
