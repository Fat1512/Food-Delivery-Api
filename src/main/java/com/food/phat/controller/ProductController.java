package com.food.phat.controller;

import com.food.phat.dto.response.ProductReponse;
import com.food.phat.dto.request.ProductRequest;
import com.food.phat.dto.response.PageResponse;
import com.food.phat.entity.Product;
import com.food.phat.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public PageResponse<Product> getProducts(
            @RequestParam(name = "page", required = false, defaultValue = "0") String page,
            @RequestParam(name = "size", required = false, defaultValue = "3") String size,
            @RequestParam(name = "categoryId", required = false) String categoryId,
            @RequestParam(name = "sortDir", required = false) String sortDir,
            @RequestParam(name = "sortBy", required = false) String sortBy,
            @RequestParam(name = "fromPrice", required = false) String fromPrice,
            @RequestParam(name = "toPrice", required = false) String toPrice
    ) {
        Map<String, String> filteredConditions = new HashMap<>();
        filteredConditions.put("page", page);
        filteredConditions.put("size", size);
        filteredConditions.put("sortDir", sortDir);
        filteredConditions.put("sortBy", sortBy);
        filteredConditions.put("categoryId", categoryId);
        filteredConditions.put("fromPrice", fromPrice);
        filteredConditions.put("toPrice", toPrice);

        return productService.getAllProducts(filteredConditions);
    }

    @PostMapping("/product")
    public ResponseEntity<ProductReponse> saveOrUpdateProduct(@RequestBody ProductRequest product) {
        ProductReponse responseProduct = productService.saveOrUpdate(product);
        return new ResponseEntity<>(responseProduct, HttpStatus.OK);
    }
}
