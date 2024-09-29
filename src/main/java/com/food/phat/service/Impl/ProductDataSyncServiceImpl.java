package com.food.phat.service.Impl;

import com.food.phat.dto.ProductDocument;
import com.food.phat.entity.Product;
import com.food.phat.mapstruct.product.ProductMapper;
import com.food.phat.repository.ProductDocumentRepository;
import com.food.phat.repository.ProductRepository;
import com.food.phat.service.ProductDataSyncService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDataSyncServiceImpl implements ProductDataSyncService {

    private final ProductDocumentRepository productDocumentRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    @Override
    @Transactional
    public void create(Integer productId) {
        Product product = productRepository.findById(productId).get();
        ProductDocument productDocument = productMapper.toProductDocument(product);
        productDocumentRepository.save(productDocument);
    }

    @Override
    @Transactional
    public void update(Integer productId) {
        Product product = productRepository.findById(productId).get();
        ProductDocument productDocument = productMapper.toProductDocument(product);
        productDocumentRepository.save(productDocument);
    }

    @Override
    @Transactional
    public void delete(Integer productId) {
        productDocumentRepository.deleteById(productId);
    }
}
