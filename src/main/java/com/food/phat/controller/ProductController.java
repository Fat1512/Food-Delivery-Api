package com.food.phat.controller;

import com.food.phat.dto.PageResponse;
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

    /**
     *
     * @param page
     * @param size
     * @param categoryId
     * @param sort
     * @param fromPrice
     * @param toPrice
     * @return <>ProductResponse</>
     */
    @GetMapping("/products")
    public PageResponse<Product> getAllProducts(
            @RequestParam(name = "page", required = false, defaultValue = "0") String page,
            @RequestParam(name = "size", required = false, defaultValue = "3") String size,
            @RequestParam(name = "category", required = false) String categoryId,
            @RequestParam(name = "sort", required = false, defaultValue = "desc") String sort,
            @RequestParam(name = "fromPrice", required = false) String fromPrice,
            @RequestParam(name = "toPrice", required = false) String toPrice
    ) {
        Map<String, String> filteredConditions = new HashMap<>();
        filteredConditions.put("page", page);
        filteredConditions.put("size", size);
        filteredConditions.put("sort", sort);
        filteredConditions.put("categoryId", categoryId);
        filteredConditions.put("fromPrice", fromPrice);
        filteredConditions.put("toPrice", toPrice);

        PageResponse<Product> products = productService.getAllProducts(filteredConditions);
        return products;
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(new Product(), HttpStatus.OK);
    }
}
