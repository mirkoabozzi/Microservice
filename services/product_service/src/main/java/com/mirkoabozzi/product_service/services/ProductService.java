package com.mirkoabozzi.product_service.services;

import com.mirkoabozzi.product_service.dto.ProductRequestDTO;
import com.mirkoabozzi.product_service.entities.Product;
import com.mirkoabozzi.product_service.exceptions.BadRequestException;
import com.mirkoabozzi.product_service.exceptions.NotFoundExceptions;
import com.mirkoabozzi.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product saveProduct(ProductRequestDTO body) {
        Product newProduct = new Product(body.name(), body.description(), body.quantityAvailable());
        return this.productRepository.save(newProduct);
    }

    public List<Product> getAllProducts() {

        return this.productRepository.findAll();
    }

    public Product findById(UUID id) {
        return this.productRepository.findById(id).orElseThrow(() -> new NotFoundExceptions("Product with id " + id + " not found on DB"));
    }

    public void deleteProduct(UUID id) {
        Product productFound = this.findById(id);
        this.productRepository.delete(productFound);
    }

    public boolean checkAvailability(UUID productId, int quantity) {
        return this.productRepository.existsByIdAndQuantityAvailableIsGreaterThanEqual(productId, quantity);
    }

    public void decreaseProductQuantity(UUID productId, int quantity) {
        Product productFound = this.findById(productId);
        if (productFound.getQuantityAvailable() < quantity)
            throw new BadRequestException("Product with id " + productId + " not available in the quantity requested");
        productFound.setQuantityAvailable(productFound.getQuantityAvailable() - quantity);
        this.productRepository.save(productFound);
    }
}
