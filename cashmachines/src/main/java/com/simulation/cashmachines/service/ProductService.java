package com.simulation.cashmachines.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.simulation.cashmachines.entity.Product;
import com.simulation.cashmachines.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductByBarcode(String id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(String id, Product product) {
        if (productRepository.existsById(id)) {
            product.setBarcode(id);
            return productRepository.save(product);
        }
        throw new RuntimeException("Product not found");
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

}