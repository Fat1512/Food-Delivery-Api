package com.food.phat.repository;

import com.food.phat.dto.ProductDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDocumentRepository extends ElasticsearchRepository<ProductDocument, Integer> {
    List<ProductDocument> findByName(String name);
}
