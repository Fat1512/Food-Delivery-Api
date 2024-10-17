package com.food.phat.controller;

import com.food.phat.dto.MessageResponse;
import com.food.phat.dto.NotificationDetailResponse;
import com.food.phat.dto.ProductDocument;
import com.food.phat.dto.product.ProductResponse;
import com.food.phat.dto.product.ProductRequest;
import com.food.phat.service.NotificationService;
import com.food.phat.service.ProductDocumentService;
import com.food.phat.service.ProductService;
import com.food.phat.utils.ApiResponseMessage;
import com.food.phat.utils.NotificationMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final NotificationService notificationService;
    private final ProductDocumentService productDocumentService;

    @GetMapping("/products")
    public ResponseEntity<MessageResponse> getProducts(@RequestParam Map<String, String> params) throws IOException {
        List<ProductDocument> productResponses = productDocumentService.getProducts(params);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_RETRIEVED.name())
                .data(productResponses)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @PutMapping("/products")
    public ResponseEntity<MessageResponse> updateProduct(@RequestBody ProductRequest product) {
        productService.update(product);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_UPDATED.name())
                .data(null)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);    }

    @PostMapping("/products")
    public ResponseEntity<MessageResponse> saveProduct(@RequestBody ProductRequest product) {
        ProductResponse responseProduct = productService.save(product);
        NotificationDetailResponse notificationDetailResponse  = notificationService.getSubscriptionDetail(product.getRestaurantId());

        Map<String, String> notificationResponse = NotificationMessage.PRODUCT.notifyMessage(notificationDetailResponse.getObjectName());

//        notificationService.send(notificationResponse.get("subject"),notificationResponse.get("content")
//                , notificationDetailResponse.getEmailList().toArray(new String[0]));
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_CREATED.name())
                .data(null)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<MessageResponse> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProductById(productId);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_DELETED.name())
                .data(null)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }
}










//@PathVariable String productKeyword,
//@RequestParam(name = "productCategoryId", required = false) String categoryId,
//@RequestParam(name = "priceSortDir", required = false) String sortDir,
//@RequestParam(name = "fromPrice", required = false) String fromPrice,
//@RequestParam(name = "toPrice", required = false) String toPrice





//    @GetMapping("/product")
//    public PageResponse<Product> getProducts(
//            @RequestParam(name = "page", required = false, defaultValue = "0") String page,
//            @RequestParam(name = "size", required = false, defaultValue = "3") String size,
//            @RequestParam(name = "categoryId", required = false) String categoryId,
//            @RequestParam(name = "sortDir", required = false) String sortDir,
//            @RequestParam(name = "sortBy", required = false) String sortBy,
//            @RequestParam(name = "fromPrice", required = false) String fromPrice,
//            @RequestParam(name = "toPrice", required = false) String toPrice
//    ) {
//        Map<String, String> filteredConditions = new HashMap<>();
//        filteredConditions.put("page", page);
//        filteredConditions.put("size", size);
//        filteredConditions.put("sortDir", sortDir);
//        filteredConditions.put("sortBy", sortBy);
//        filteredConditions.put("categoryId", categoryId);
//        filteredConditions.put("fromPrice", fromPrice);
//        filteredConditions.put("toPrice", toPrice);
//
//        return productService.getAllProducts(filteredConditions);
//    }