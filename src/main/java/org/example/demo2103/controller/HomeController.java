package org.example.demo2103.controller;

import org.example.demo2103.Repository.ProductRepository;
import org.example.demo2103.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class HomeController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public ResponseEntity findAll() {
        List<Product> productList = productRepository.findAll();
        return new ResponseEntity(productList, HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity save(@RequestBody Product product) {
        productRepository.save(product);
        return new ResponseEntity<>("ok", HttpStatus.CREATED);
    }
    //
    @PutMapping("/{id}/update")
    public ResponseEntity update(@PathVariable int id, @RequestBody Product product) {
        Optional<Product> productOptional = productRepository.findById(id);
        product.setId(productOptional.get().getId());
        productRepository.save(product);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity delete(@PathVariable int id) {
        productRepository.deleteById(id);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @GetMapping("/searchByPrice")
    public ResponseEntity searchPrice(@RequestParam int price1,@RequestParam int price2) {
        List<Product> productList = productRepository.findAllByPriceBetween(price1, price2);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/findByAmountAsc")
    public ResponseEntity findAllByOrderByAmountAsc() {
        List<Product> productList = productRepository.findAllByOrderByAmountAsc();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/findByTop3")
    public ResponseEntity findByPriceTop3() {
        List<Product> productList = productRepository.findAllByPrice();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/findProductByCategory")
    public ResponseEntity findProductByCategory(String name) {
        List<Product> productList = productRepository.findAllByCategoryName(name);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }


}
