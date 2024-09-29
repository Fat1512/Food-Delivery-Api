package com.food.phat.service.Impl;

import com.food.phat.dto.product.ProductResponse;
import com.food.phat.repository.ProductDocumentRepository;
import com.food.phat.service.ProductDocumentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;
import org.elasticsearch.client.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductDocumentServiceImpl implements ProductDocumentService {

    private final ProductDocumentRepository productDocumentRepository;

    @Override
    @Transactional
    public List<ProductResponse> getProductsByKeyword(String keyword) {
        return List.of();
    }

    @Override
    @Transactional
    public List<?> getProducts(Map<String, String> params) {

        String productCategoryId = params.get("productCategoryId");
        String query = params.get("query");
        String fromPrice = params.get("fromPrice");
        String toPrice = params.get("toPrice");
        String priceSortDir = params.get("priceSortDir");



        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("productCategoryId", "6"))
                .must(QueryBuilders.multiMatchQuery("iphone", "title", "description")
                        .fuzziness(Fuzziness.AUTO))
                .filter(QueryBuilders.rangeQuery("price")
                        .gte(1000)
                        .lte(10000));
        SortBuilder<FieldSortBuilder> sortBuilders = SortBuilders.fieldSort("price").order(SortOrder.ASC);

        var searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.sort(sortBuilders);

        var searchRequest = new SearchRequest();
        searchRequest.source(searchSourceBuilder);

        var client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        var hits = searchResponse.getHits().getHits();
        return Arrays.stream(hits).map(SearchHit::getSourceAsMap).toList();
    }

    @Override
    @Transactional
    public ProductResponse getProduct(Integer productId) {
        return null;
    }
}
