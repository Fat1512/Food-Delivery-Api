package com.food.phat.service.Impl;

import com.food.phat.dto.request.product.ProductRequest;
import com.food.phat.dto.response.PageResponse;
import com.food.phat.entity.Product;
import com.food.phat.repository.CategoryRepository;
import com.food.phat.repository.ModifierRepository;
import com.food.phat.repository.ProductRepository;
import com.food.phat.repository.RestaurantRepository;
import com.food.phat.service.ProductService;
import com.food.phat.specification.FilterRequest;
import com.food.phat.specification.Operator;
import com.food.phat.specification.SearchSpecification;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    RestaurantRepository restaurantRepository;
    CategoryRepository categoryRepository;
    ModifierRepository modifierRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, RestaurantRepository restaurantRepository) {
        this.productRepository = productRepository;
        this.restaurantRepository = restaurantRepository;
    }

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
        Page<Product> products = productRepository.findAll(specs, pageable);

        return new PageResponse<>(
                products.getContent(),
                page,
                size,
                products.getTotalElements(),
                products.getTotalPages(),
                products.isLast());
    }

    @Override
    @Transactional
    public Product getProductById(int id) {
        return productRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Product getProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    @Transactional
    public Product saveOrUpdate(ProductRequest productRequest) {
        Product product = mapProductRequestToProduct(productRequest);
        return productRepository.save(product);
    }

    private Product mapProductRequestToProduct(ProductRequest productRequest) {
        Product product = productRepository.findById(productRequest.getProductId()).orElse(new Product());
        product.setName(productRequest.getName());
        product.setStatus(productRequest.getStatus());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setThumbnail(productRequest.getThumbnail());
        productRequest.getModifierIdList().stream()
                .map(id -> modifierRepository.findById(id).get())
                .collect(Collectors.toCollection(ArrayList::new));
        product.setCategory(categoryRepository.findById(productRequest.getCategoryId()).get());
        product.setRestaurant(restaurantRepository.findById(productRequest.getRestaurantId()).get());
        return product;
    }
}
