package com.Project.BookShop.Service;

import com.Project.BookShop.DTO.CustomerDTO;
import com.Project.BookShop.DTO.OrderDTO;
import com.Project.BookShop.Entity.CustomerEntity;
import com.Project.BookShop.Entity.OrderEntity;
import com.Project.BookShop.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public CustomerDTO CreateCustomer(CustomerDTO customerDTO){
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customerDTO.getName());
        customerEntity.setPhone(customerDTO.getPhone());
        customerEntity.setAddress(customerDTO.getAddress());

        customerRepository.save(customerEntity);
        return customerDTO;
    }

    public CustomerDTO GetCustomer(long id){
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findById(id);
        if(customerEntityOptional.isEmpty()){
            throw new RuntimeException("Customer not found");
        }
        else {
            CustomerEntity customerEntity = customerEntityOptional.get();
            List<OrderEntity> orderEntities = customerEntity.getOrderEntityList();
            List<OrderDTO> orderDTOS = new ArrayList<>();

            for (OrderEntity elements : orderEntities){

                OrderDTO orderDTO = new OrderDTO(elements.getOrderStatus(), elements.getDateTime(),customerEntity.getName());
                orderDTOS.add(orderDTO);
            }
            return new CustomerDTO(customerEntity.getName(),customerEntity.getPhone(),customerEntity.getAddress(),orderDTOS);
        }
    }

    public List<CustomerEntity> GetAllCustomers(){
        return customerRepository.findAll();
    }

    public CustomerDTO UpdateCustomer(CustomerDTO customerDTO, long id){
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findById(id);
        if (customerEntityOptional.isEmpty()){
            throw new RuntimeException("Customer not found");
        }
        else{
            CustomerEntity customerEntity = customerEntityOptional.get();
            customerEntity.setName(customerDTO.getName());
            customerEntity.setPhone(customerDTO.getPhone());
            customerEntity.setAddress(customerDTO.getAddress());

            customerRepository.save(customerEntity);
            return customerDTO;
        }
    }

    public CustomerDTO DeleteCustomer(long id){
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findById(id);
        if (customerEntityOptional.isEmpty()){
            throw new RuntimeException("Customer not found");
        }
        else{
            CustomerEntity customerEntity = customerEntityOptional.get();

            List<OrderDTO> orderDTOS = new ArrayList<>();
            List<OrderEntity> orderEntities = customerEntity.getOrderEntityList();

            for (OrderEntity elements : orderEntities){

                OrderDTO orderDTO = new OrderDTO(elements.getOrderStatus(), elements.getDateTime(),customerEntity.getName());
                orderDTOS.add(orderDTO);
            }

            customerRepository.delete(customerEntity);

            return new CustomerDTO(customerEntity.getName(), customerEntity.getPhone(), customerEntity.getAddress(), orderDTOS);
        }
    }
}
