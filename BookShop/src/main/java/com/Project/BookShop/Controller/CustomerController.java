package com.Project.BookShop.Controller;

import com.Project.BookShop.DTO.CustomerDTO;
import com.Project.BookShop.Entity.CustomerEntity;
import com.Project.BookShop.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/customer/create")
    public CustomerDTO CreateCustomer(@RequestBody CustomerDTO customerDTO){return customerService.CreateCustomer(customerDTO);}

    @GetMapping("/customer/{id}")
    public CustomerDTO GetCustomer(@PathVariable long id){return  customerService.GetCustomer(id);}

    @GetMapping("/customers/")
    public List<CustomerEntity> GetAllCustomer(){return  customerService.GetAllCustomers();}

    @PutMapping("/customer/update/{id}")
    public CustomerDTO UpdateCustomer(@PathVariable long id, @RequestBody CustomerDTO customerDTO){return  customerService.UpdateCustomer(customerDTO,id);}

    @DeleteMapping("/customer/delete/{id}")
    public CustomerDTO DeleteCustomer(@PathVariable long id){return customerService.DeleteCustomer(id);}

}
