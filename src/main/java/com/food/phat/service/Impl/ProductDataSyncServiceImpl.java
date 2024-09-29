package com.food.phat.service.Impl;

import com.food.phat.repository.ProductDocumentRepository;
import com.food.phat.repository.ProductRepository;
import com.food.phat.service.ProductDataSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDataSyncServiceImpl implements ProductDataSyncService {

    private final ProductDocumentRepository productDocumentRepository;
    private final ProductRepository productRepository;

    @Override
    public void create(Integer productId) {

    }

    @Override
    public void update(Integer productId) {

    }

    @Override
    public void delete(Integer productId) {

    }
}
