package com.mirkoabozzi.product_service.controller;

import com.mirkoabozzi.product_service.dto.ProductRequestDTO;
import com.mirkoabozzi.product_service.dto.ProductRespDTO;
import com.mirkoabozzi.product_service.entities.Product;
import com.mirkoabozzi.product_service.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ProductRespDTO saveProduct(@RequestBody ProductRequestDTO body) {

        Product newProduct = this.productService.saveProduct(body);
        return new ProductRespDTO(newProduct.getId(), newProduct.getName(), newProduct.getDescription(), newProduct.getQuantityAvailable());
    }

    @GetMapping
    public List<ProductRespDTO> getAllProducts() {
        List<Product> productList = this.productService.getAllProducts();
        return productList.stream().map(product -> new ProductRespDTO(product.getId(), product.getName(), product.getDescription(), product.getQuantityAvailable())).toList();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable UUID id) {
        this.productService.deleteProduct(id);
    }

    @GetMapping("/check")
    public boolean checkAvailability(@RequestParam UUID productId, @RequestParam int quantity) {
        return this.productService.checkAvailability(productId, quantity);
    }
}
