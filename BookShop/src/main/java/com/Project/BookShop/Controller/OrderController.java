package com.Project.BookShop.Controller;

import com.Project.BookShop.DTO.CustomerDTO;
import com.Project.BookShop.DTO.OrderDTO;
import com.Project.BookShop.Entity.OrderEntity;
import com.Project.BookShop.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/order/{customer}-{book}/create")
    public OrderDTO CreateOrder(@PathVariable long customer,@PathVariable long book){return orderService.CreateOrder(customer, book);}

    @GetMapping("/order/{id}")
    public OrderDTO GetOrder(@PathVariable long id){return  orderService.GetOrder(id);}

    @PostMapping("/addBook/{order}-{book}/create")
    public OrderDTO AddBookToOrder(@PathVariable long order,@PathVariable long book){return orderService.AddBookToOrder(order, book);}

    @GetMapping("/orders/")
    public List<OrderEntity> GetAllOrder(){return  orderService.GetAllOrders();}

    @DeleteMapping("/order/delete/{id}")
    public OrderDTO DeleteCustomer(@PathVariable long id){return orderService.DeleteOrder(id);}
}
