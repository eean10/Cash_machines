package com.simulation.cashmachines.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import com.simulation.cashmachines.entity.Product;
import com.simulation.cashmachines.service.ProductService;

@RestController
public class ProductController {
    
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{barcode}")
    public Product getProductBybarcode(@PathVariable String barcode) {
        return productService.getProductByBarcode(barcode);
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/products/{barcode}")
    public Product updateProduct(@PathVariable String barcode, @RequestBody Product product) {
        return productService.updateProduct(barcode, product);
    }

    @DeleteMapping("/products/{barcode}")
    public void deleteProduct(@PathVariable String barcode) {
        productService.deleteProduct(barcode);
    }


}