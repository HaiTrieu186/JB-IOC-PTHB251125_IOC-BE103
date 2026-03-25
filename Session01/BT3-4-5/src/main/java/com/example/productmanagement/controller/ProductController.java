package com.example.productmanagement.controller;

import com.example.productmanagement.model.Product;
import com.example.productmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping("/api/products")
    public List<Product> getProductList(){
        return productService.getAllProducts();
    };

    @PostMapping("/api/products")
    public String addProduct (@RequestBody Product product){
        return productService.addProduct(product);
    };

    @PutMapping("/api/products/{id}")
    public String updateProduct(@PathVariable int id,@RequestBody Product product){
        return productService.updateProduct(product);

    };

    @DeleteMapping("/api/products/{id}")
    public String deleteProduct(@PathVariable int id){
        return productService.removeProduct(id);
    }


}
