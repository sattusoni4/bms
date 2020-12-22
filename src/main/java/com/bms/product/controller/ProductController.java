package com.bms.product.controller;

import com.bms.product.domain.Product;
import com.bms.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api-reactive")
@AllArgsConstructor
public class ProductController {


    private final ProductService productService;

    @GetMapping("hello/product")
    public Mono<String> helloProduct() {
        return Mono.just("Hello In BMS");
    }

    @GetMapping("products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Product>> products(@PathVariable String id) {
        return productService.getCustomerById(id)
                .map(customer -> new ResponseEntity<>(customer, HttpStatus.OK));
    }

    @PostMapping("products")
    public Mono<Product> addProducts(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("products/{id}")
    public Mono<ResponseEntity<Product>> updateProducts(@PathVariable String id, @RequestBody Product productDto) {
        return productService.updateProduct(id, productDto)
                .map(product -> new ResponseEntity<>(product, HttpStatus.ACCEPTED));
    }
}
