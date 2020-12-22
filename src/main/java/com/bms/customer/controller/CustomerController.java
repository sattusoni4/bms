package com.bms.customer.controller;

import com.bms.customer.domain.Customer;
import com.bms.customer.dto.CustomerDto;
import com.bms.customer.service.CustomerService;
import lombok.AllArgsConstructor;
//import ma.glasnost.orika.MapperFacade;
//import ma.glasnost.orika.MapperFactory;
//import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api-reactive")
@AllArgsConstructor
public class CustomerController {


    private final CustomerService customerService;

//    MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    @GetMapping("hello")
    public Mono<String> helloBms() {
//        mapperFactory.classMap(CustomerDto.class, Customer.class);
//        MapperFacade mapper = mapperFactory.getMapperFacade();
        CustomerDto customerDto = CustomerDto.builder()
                .id("123")
                .name("satyendra")
                .mobile("8512019792")
                .village("Maharajganj")
                .totalPrice(390.89)
                .build();
//        Customer customer = mapper.map(customerDto, Customer.class);
//        System.out.println("customer = " + customer);
        return Mono.just("Hello In BMS");
    }

    @GetMapping("customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Customer>> customers(@PathVariable String id) {
        return customerService.getCustomerById(id)
                .map(customer -> new ResponseEntity<>(customer, HttpStatus.OK));
    }

    @PostMapping("customers")
    public Mono<Customer> addCustomers(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PutMapping("customers/{id}")
    public Mono<ResponseEntity<Customer>> updateCustomer(@PathVariable String id, @RequestBody Customer customerDto) {
        return customerService.updateCustomer(id, customerDto)
                .map(customer -> new ResponseEntity<>(customer, HttpStatus.ACCEPTED));
    }
}
