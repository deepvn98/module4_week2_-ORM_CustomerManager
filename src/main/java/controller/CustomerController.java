package controller;


import model.Customer;
import model.CustomerUploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.customer.ICustomerService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("Customers/")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private Environment  environment;

    @GetMapping("Show")
    public ModelAndView showAll(){
        ModelAndView modelAndView = new ModelAndView("home");
        List<Customer> customerList = customerService.getAll();
        modelAndView.addObject("Customer",customerList);
        return modelAndView;
    }

    @GetMapping("Create")
    public ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("obj",new CustomerUploadFile());
        return modelAndView;
    }

    @PostMapping("Create")
    public String create(CustomerUploadFile customerUploadFile){
        MultipartFile multipartFile = customerUploadFile.getImg();
        String nameFile = multipartFile.getOriginalFilename();
        String localFile = environment.getProperty("uploadFile");
        try {
            FileCopyUtils.copy(multipartFile.getBytes(),new File(localFile+ nameFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Customer customer = new Customer();
        customer.setId((int) (Math.random()*10000));
        customer.setName(customerUploadFile.getName());
        customer.setAddress(customerUploadFile.getAddress());
        customer.setImg(nameFile);
        customerService.save(customer);
        return "redirect:Show";
    }

//    @GetMapping("{id}/Remove")
    @GetMapping("Remove")
    public String remove(int id){
        customerService.delete(id);
        return "redirect:Show";
    }

    @GetMapping ("Update")
    public ModelAndView updateForm(int id){
        Customer customer = customerService.findByID(id);
        ModelAndView modelAndView = new ModelAndView("update");
        modelAndView.addObject("customer",customer);
//        CustomerUploadFile customerUploadFile = new CustomerUploadFile();
        modelAndView.addObject("customerF",new CustomerUploadFile());
        return modelAndView;
    }

    @PostMapping("Update")
    public String update(CustomerUploadFile customerUploadFile){
        MultipartFile multipartFile = customerUploadFile.getImg();
        String nameFile = multipartFile.getOriginalFilename();
        String localFile = environment.getProperty("uploadFile");
        try {
            FileCopyUtils.copy(multipartFile.getBytes(),new File(localFile+nameFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Customer customer = new Customer();
        customer.setId(customerUploadFile.getId());
        customer.setName(customerUploadFile.getName());
        customer.setAddress(customerUploadFile.getAddress());
        customer.setImg(nameFile);
        customerService.update(customer.getId(),customer);
        return "redirect:Show";
    }

}
