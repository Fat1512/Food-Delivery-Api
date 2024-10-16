package com.food.phat.service.Impl;

import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.json.JsonData;
import com.food.phat.dto.ProductDocument;
import com.food.phat.repository.ProductDocumentRepository;
import com.food.phat.service.ProductDocumentService;
import jakarta.transaction.Transactional;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
//@RequiredArgsConstructor
public class ProductDocumentServiceImpl implements ProductDocumentService {

    private ProductDocumentRepository productDocumentRepository;
    private ElasticsearchOperations client;
    @Override
    @Transactional
    public List<?> getProducts(Map<String, String> params) throws IOException {

        String productCategoryId = params.get("productCategoryId");
        String query = params.get("query");
        String fromPrice = params.get("fromPrice");
        String toPrice = params.get("toPrice");
        String priceSortDir = params.get("priceSortDir");

        var boolQueryBuilder = QueryBuilders.bool();

        if(productCategoryId != null){
        boolQueryBuilder
                    .must(builder -> builder.match(m -> m.field("productCategoryId")
                    .query(productCategoryId)));
        }

        boolQueryBuilder.must(builder -> builder
                .multiMatch(mm -> mm.fields("title, description")
                        .query(query)
                        .fuzziness("AUTO")
                        .maxExpansions(30)));

        if(fromPrice != null && toPrice != null){
            boolQueryBuilder.filter(q -> q.range(v -> v.field("price")
                    .lte(JsonData.of(toPrice))
                    .gte(JsonData.of(fromPrice))));
        }

        NativeQuery nativeQuery = new NativeQueryBuilder()
                .withQuery(boolQueryBuilder.build()._toQuery())
                .withSort(sort -> {
                    if("desc".equals(priceSortDir)) {
                        return sort.field(f -> f.field("price").order(SortOrder.Desc));
                    }
                    return sort.field(f -> f.field("price").order(SortOrder.Asc));
                })
                .build();
        return client.search(nativeQuery, ProductDocument.class).stream().map(SearchHit::getContent).toList();
    }
}
