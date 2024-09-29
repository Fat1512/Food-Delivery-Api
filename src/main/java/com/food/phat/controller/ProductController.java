package com.food.phat.controller;

import com.food.phat.dto.APIResponse;
import com.food.phat.dto.product.ProductResponse;
import com.food.phat.dto.product.ProductRequest;
import com.food.phat.repository.ProductDocumentRepository;
import com.food.phat.service.NotificationService;
import com.food.phat.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.elasticsearch.client.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final NotificationService notificationService;
    private final ProductDocumentRepository productDocumentRepository;

    @GetMapping("/products/{productKeyword}")
    public ResponseEntity<?> getProducts(Map<String, String> params) throws IOException {


        return new ResponseEntity<>(, HttpStatus.OK);
    }

    @PutMapping("/products")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest product) {
        ProductResponse responseProduct = productService.update(product);
        return new ResponseEntity<>(responseProduct, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductResponse> saveProduct(@RequestBody ProductRequest product) {
        ProductResponse responseProduct = productService.save(product);
//        NotificationDetailResponse notificationDetailResponse  = notificationService.getSubscriptionDetail(product.getRestaurantId());
//
//        Map<String, String> notificationResponse = NotificationMessage.PRODUCT.notifyMessage(notificationDetailResponse.getObjectName());
//
//        notificationService.send(notificationResponse.get("subject"),notificationResponse.get("content")
//                , notificationDetailResponse.getEmailList().toArray(new String[0]));
        return new ResponseEntity<>(responseProduct, HttpStatus.OK);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<APIResponse> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProductById(productId);
        return new ResponseEntity<>(new APIResponse(Boolean.TRUE, "successfully deleted"), HttpStatus.OK);
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