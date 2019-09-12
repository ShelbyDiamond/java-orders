package com.lambdaschool.orders.controller;

import com.lambdaschool.orders.model.Customers;
import com.lambdaschool.orders.services.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private CustomersService customersService;

    // localhost:2019/data/customer/new
    @PostMapping(value = "/customer/new",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addNewCustomer(@Valid
                                            @RequestBody
                                                    Customers customers){
        customers = customersService.save(customers);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/customer/name/{custname}").buildAndExpand(customers.getCustname()).toUri();
        responseHeaders.setLocation(newCustomerURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/customer/update/{custcode}",
            consumes = {"application/json"})
    public ResponseEntity<?> updateCustomer(@RequestBody
                                                    Customers updateCustomers, @PathVariable long custcode) {
        customersService.update(updateCustomers, custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/customer/delete/{custcode}")
    public ResponseEntity<?> deleteCustomer(@PathVariable long custcode) {
        customersService.delete(custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
