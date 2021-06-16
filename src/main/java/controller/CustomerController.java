package controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.customer.ICustomerService;

@Controller
@RequestMapping("Customers/")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private Environment  environment;





}
