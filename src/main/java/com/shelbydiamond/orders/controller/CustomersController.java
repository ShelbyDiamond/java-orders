package com.lambdaschool.orders.controller;

import com.lambdaschool.orders.model.Customers;
import com.lambdaschool.orders.services.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomersController {

    @Autowired
    private CustomersService customersService;

    @GetMapping(value = "/order",
            produces = {"application/json"})
    public ResponseEntity<?> getAllCustomersAndOrders() {
        List<Customers> myCustomers = customersService.findAllCustomersAndOrders();
        return new ResponseEntity<>(myCustomers, HttpStatus.OK);
    }

    @GetMapping(value = "/name/{custname}",
            produces = {"application/json"})
    public ResponseEntity<?> getCustomerByName(@PathVariable String custname) {
        Customers c = customersService.findCustomersByName(custname);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }
}
