package com.bms.product.service;

import com.bms.common.constants.ErrorConstants;
import com.bms.customer.domain.Customer;
import com.bms.customer.repository.CustomerRepository;
import com.bms.error.domain.ErrorBo;
import com.bms.error.domain.ErrorDetailBo;
import com.bms.error.exception.ServiceException;
import com.bms.product.domain.Product;
import com.bms.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Mono<Product> getCustomerById(String id) {
        return productRepository
                .findById(id)
                .doOnError(error -> {
                    log.error("Error while fetching product with id={}.", id);
                    ErrorBo errorBo = ErrorBo.builder().status(HttpStatus.NO_CONTENT)
                            .message("Error while fetching product with id=".concat(id))
                            .details(List.of(ErrorDetailBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR)
                                    .message(error.getMessage())
                                    .build())).build();
                    throw new ServiceException(errorBo);
                });
    }

    public Mono<Product> createProduct(Product product) {
        return productRepository.save(product);
    }

    public Mono<Product> updateProduct(String id, Product product) {
        return productRepository
                .findById(id)
                .flatMap(entity -> {
                    return productRepository.save(entity);
                });
    }

}
