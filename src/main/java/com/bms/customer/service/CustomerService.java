package com.bms.customer.service;

import com.bms.common.constants.ErrorConstants;
import com.bms.customer.domain.Customer;
import com.bms.error.domain.ErrorBo;
import com.bms.error.domain.ErrorDetailBo;
import com.bms.error.exception.ServiceException;
import com.bms.customer.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class CustomerService {

    final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Mono<Customer> getCustomerById(String id) {
        return customerRepository
                .findById(id)
                .doOnError(error -> {
                    log.error("Error while fetching customer with id={}.", id);
                    ErrorBo errorBo = ErrorBo.builder().status(HttpStatus.NO_CONTENT)
                            .message("Error while fetching customer with id=".concat(id))
                            .details(List.of(ErrorDetailBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR)
                                    .message(error.getMessage())
                                    .build())).build();
                    throw new ServiceException(errorBo);
                });
    }

    public Mono<Customer> createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Mono<Customer> updateCustomer(String id, Customer customer) {
        return customerRepository
                .findById(id)
                .flatMap(customerRepository::save)
                ;
    }

}
