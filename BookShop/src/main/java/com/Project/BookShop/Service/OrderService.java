package com.Project.BookShop.Service;

import com.Project.BookShop.DTO.CustomerDTOWithoutOrder;
import com.Project.BookShop.DTO.OrderDTO;
import com.Project.BookShop.Entity.BookEntity;
import com.Project.BookShop.Entity.OrderListEntity;
import com.Project.BookShop.Entity.CustomerEntity;
import com.Project.BookShop.Entity.OrderEntity;
import com.Project.BookShop.Repository.BookRepository;
import com.Project.BookShop.Repository.CustomerRepository;
import com.Project.BookShop.Repository.OrderListRepository;
import com.Project.BookShop.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    OrderListRepository orderListRepository;

    public OrderDTO CreateOrder(long customerId, long bookId){
        List<OrderEntity> orderlist;
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findById(customerId);
        Optional<BookEntity> bookEntityOptional = bookRepository.findById(bookId);
        if (customerEntityOptional.isEmpty()||bookEntityOptional.isEmpty()){
            throw new RuntimeException("No Customer or Book found");
        }
        else{
            CustomerEntity customerEntity = customerEntityOptional.get();
            BookEntity bookEntity = bookEntityOptional.get();
            OrderListEntity orderListEntity = new OrderListEntity();

            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setOrderStatus("Pending");
            orderEntity.setDateTime(LocalDateTime.now());
            orderEntity.setCustomer(customerEntity);

//            List<BookEntity> bookEntities = new ArrayList<>();
//            bookEntities.add(bookEntity);
//            List<OrderEntity> orderEntities = new ArrayList<>();
//            orderEntities.add(orderEntity);
//            List<CustomerEntity> customerEntities = new ArrayList<>();
//            customerEntities.add(customerEntity);
            List<OrderListEntity> orderListEntities = new ArrayList<>();
            orderListEntities.add(orderListEntity);

            orderListEntity.setBook(bookEntity);
            orderListEntity.setOrder(orderEntity);
            orderListEntity.setCustomer(customerEntity);

            orderEntity.setOrderList(orderListEntities);
            orderlist = customerEntity.getOrderEntityList();
            orderlist.add(orderEntity);


            customerEntity.setOrderEntityList(orderlist);


            CustomerDTOWithoutOrder customerDTOWithoutOrder = new CustomerDTOWithoutOrder(customerEntity.getName(), customerEntity.getPhone(), customerEntity.getAddress());
            OrderDTO orderDTO = new OrderDTO("Pending",LocalDateTime.now(),customerDTOWithoutOrder.getName());
            bookRepository.save(bookEntity);
            customerRepository.save(customerEntity);
            orderRepository.save(orderEntity);
            orderListRepository.save(orderListEntity);
            return orderDTO;
        }
    }

    public OrderDTO AddBookToOrder(long orderId, long bookId){
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(orderId);
        Optional<BookEntity> bookEntityOptional = bookRepository.findById(bookId);
        if (orderEntityOptional.isEmpty()||bookEntityOptional.isEmpty()){
            throw new RuntimeException("No Order or Book found");
        }
        else {
            OrderEntity orderEntity = orderEntityOptional.get();
            BookEntity bookEntity = bookEntityOptional.get();
            OrderListEntity orderListEntity = new OrderListEntity();

            List<OrderListEntity> orderListEntities = new ArrayList<>();
            orderListEntities.add(orderListEntity);

            orderListEntity.setBook(bookEntity);
            orderListEntity.setOrder(orderEntity);
            orderListEntity.setCustomer(orderEntity.getCustomer());
            orderEntity.setOrderList(orderListEntities);



            bookRepository.save(bookEntity);
            orderRepository.save(orderEntity);
            orderListRepository.save(orderListEntity);
            return new OrderDTO(orderEntity.getOrderStatus(), orderEntity.getDateTime(), orderListEntity.getCustomer().getName());
        }
    }

    public OrderDTO GetOrder(long id){
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(id);
        if(orderEntityOptional.isEmpty()){
            throw new RuntimeException("Order not found");
        }
        else {
            OrderEntity orderEntity = orderEntityOptional.get();
            CustomerEntity customerEntity = orderEntity.getCustomer();

            CustomerDTOWithoutOrder customerDTOWithoutOrder = new CustomerDTOWithoutOrder(customerEntity.getName(), customerEntity.getPhone(), customerEntity.getAddress());
            return new OrderDTO(orderEntity.getOrderStatus(),orderEntity.getDateTime(),customerDTOWithoutOrder.getName());
        }
    }

    public List<OrderEntity> GetAllOrders(){
        return orderRepository.findAll();
    }


    public OrderDTO DeleteOrder(long id){
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(id);
        if (orderEntityOptional.isEmpty()){
            throw new RuntimeException("Order not found");
        }
        else{
            OrderEntity orderEntity = orderEntityOptional.get();
            CustomerEntity customerEntity = orderEntity.getCustomer();
            orderRepository.delete(orderEntity);

            CustomerDTOWithoutOrder customerDTOWithoutOrder = new CustomerDTOWithoutOrder(customerEntity.getName(), customerEntity.getPhone(), customerEntity.getAddress());
            return new OrderDTO(orderEntity.getOrderStatus(),orderEntity.getDateTime(),customerDTOWithoutOrder.getName());
        }
    }
}
