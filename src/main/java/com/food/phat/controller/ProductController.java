package com.food.phat.controller;

import com.food.phat.dto.APIResponse;
import com.food.phat.dto.NotificationDetailResponse;
import com.food.phat.dto.product.ProductReponse;
import com.food.phat.dto.product.ProductRequest;
import com.food.phat.dto.PageResponse;
import com.food.phat.entity.Product;
import com.food.phat.service.NotificationService;
import com.food.phat.service.ProductService;
import com.food.phat.utils.NotificationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class ProductController {

    private final ProductService productService;
    private final NotificationService notificationService;

    @Autowired
    public ProductController(ProductService productService, NotificationService notificationService) {
        this.productService = productService;
        this.notificationService = notificationService;
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

    @PutMapping("/product")
    public ResponseEntity<ProductReponse> updateProduct(@RequestBody ProductRequest product) {
        ProductReponse responseProduct = productService.update(product);
        return new ResponseEntity<>(responseProduct, HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<ProductReponse> saveProduct(@RequestBody ProductRequest product) {
        ProductReponse responseProduct = productService.save(product);
//        NotificationDetailResponse notificationDetailResponse  = notificationService.getSubscriptionDetail(product.getRestaurantId());
//
//        Map<String, String> notificationResponse = NotificationMessage.PRODUCT.notifyMessage(notificationDetailResponse.getObjectName());
//
//        notificationService.send(notificationResponse.get("subject"),notificationResponse.get("content")
//                , notificationDetailResponse.getEmailList().toArray(new String[0]));
        return new ResponseEntity<>(responseProduct, HttpStatus.OK);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<APIResponse> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProductById(productId);
        return new ResponseEntity<>(new APIResponse(Boolean.TRUE, "successfully deleted"), HttpStatus.OK);
    }
}
