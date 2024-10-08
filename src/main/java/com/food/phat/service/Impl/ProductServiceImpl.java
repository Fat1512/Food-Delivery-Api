package com.food.phat.service.Impl;

import com.food.phat.dto.product.ProductResponse;
import com.food.phat.dto.product.ProductRequest;
import com.food.phat.dto.PageResponse;
import com.food.phat.entity.Product;
import com.food.phat.mapstruct.product.ProductMapper;
import com.food.phat.repository.*;
import com.food.phat.service.ProductService;
import com.food.phat.specification.FilterRequest;
import com.food.phat.specification.Operator;
import com.food.phat.specification.SearchSpecification;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    @Override
    @Transactional
    public PageResponse<Product> getAllProducts(Map<String, String> filteredCondition) {
        int page = Integer.parseInt(filteredCondition.get("page"));
        int size = Integer.parseInt(filteredCondition.get("size"));
        String sortDir = filteredCondition.get("sortDir");

        Integer categoryId = filteredCondition.get("categoryId") == null ? null : Integer.parseInt(filteredCondition.get("categoryId"));
        Float fromPrice = filteredCondition.get("fromPrice") == null ? null : Float.parseFloat(filteredCondition.get("fromPrice"));
        Float toPrice = filteredCondition.get("toPrice") == null ? null : Float.parseFloat(filteredCondition.get("toPrice"));

        //-------------------------------------Filtering-------------------------------------
        FilterRequest<Product> categoryRequest = new FilterRequest<>() {
            @Override
            public Expression setExpression(Root root) {
                return root.get("category").get("categoryId");
            }
        };
        categoryRequest.setValue(categoryId);
        categoryRequest.setOperator(Operator.EQUAL);

        FilterRequest<Product> priceRequest = new FilterRequest<>() {
            @Override
            public Expression setExpression(Root root) {
                return root.get("price");
            }
        };
        priceRequest.setValue(fromPrice);
        priceRequest.setValueTo(toPrice);
        priceRequest.setOperator(Operator.BETWEEN);

        //-------------------------------------Sorting-------------------------------------
        Sort sort = Sort.by(Sort.DEFAULT_DIRECTION, "productId");
        if(sortDir == "ASC") sort = Sort.by(Sort.Direction.ASC, filteredCondition.get("sortBy"));
        else if(sortDir == "DESC") sort = Sort.by(Sort.Direction.ASC, filteredCondition.get("sortBy"));

        //-------------------------------------Specification-------------------------------------
        SearchSpecification<Product> specs = new SearchSpecification<>(new ArrayList<>(List.of(categoryRequest, priceRequest)));
        Pageable pageable = PageRequest.of(page, size, sort);
//        Page<Product> products = productRepository.findAll(specs, pageable);
//        return new PageResponse<>(
//                products.getContent(),
//                page,
//                size,
//                products.getTotalElements(),
//                products.getTotalPages(),
//                products.isLast());
        return null;
    }

    @Override
    @Transactional
    public ProductResponse getProductById(int id) {
        Product product = productRepository.findById(id).get();
        return mapper.toDto(product);
    }

    @Override
    @Transactional
    public ProductResponse update(ProductRequest productRequest) {
        Product product = productRepository.findById(productRequest.getProductId()).orElse(new Product());
        mapper.updateEntity(productRequest, product);
        productRepository.save(product);
        return mapper.toDto(product);
    }

    @Override
    @Transactional
    public ProductResponse save(ProductRequest productRequest) {
        Product product = mapper.toEntity(productRequest);
        product = productRepository.save(product);
        return mapper.toDto(product);
    }

    @Override
    @Transactional
    public void deleteProductById(int id) {
        productRepository.deleteById(id);
    }
}