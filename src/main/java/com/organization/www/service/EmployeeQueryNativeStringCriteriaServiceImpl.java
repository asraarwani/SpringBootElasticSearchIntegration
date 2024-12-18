package com.organization.www.service;

import com.organization.www.modal.Employee;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexedObjectInformation;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeQueryNativeStringCriteriaServiceImpl implements EmployeeQueryNativeStringCriteriaService {

    final private ElasticsearchOperations elasticsearchOperations;


    public EmployeeQueryNativeStringCriteriaServiceImpl(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Override
    public SearchHits<Employee> searchByNameUsingCriteria(String firstName) {
        Criteria criteria = new Criteria("firstName").is(firstName);
        Query searchQuery = new CriteriaQuery(criteria);
        System.out.println("Search using Criteria.....");
        return elasticsearchOperations.search(searchQuery, Employee.class);
    }

    @Override
    public SearchHits<Employee> searchByNameUsingNativeQuery(String firstName) {
        Query query = NativeQuery.builder()
                .withQuery(q -> q.match(m -> m.field("firstName").query(firstName))).build();
        System.out.println("Search using Native Query.....");
        return elasticsearchOperations.search(query, Employee.class);
    }

    @Override
    public SearchHits<Employee> searchByNameUsingQuery(String firstName) {
        Query query = new StringQuery(
                "{ \"match\": { \"firstName\": { \"query\": \"" + firstName + " \" } } } ");
        System.out.println("Search using Query.....");
        return elasticsearchOperations.search(query, Employee.class);
    }

    @Override
    public List<String> bulkUpload(List<Employee> list) {
        List<String> idList = null;
        List<IndexQuery> indexQueries = new ArrayList<>();
        list.stream().forEach(employee -> {
            IndexQuery indexQuery = new IndexQuery();
            indexQuery.setObject(employee);
            indexQueries.add(indexQuery);
        });
        List<IndexedObjectInformation> info = elasticsearchOperations.bulkIndex(indexQueries, Employee.class);
        Set<String> set = info.stream().map(IndexedObjectInformation::id).collect(Collectors.toSet());
        idList = new ArrayList<>(set);
        return idList;
    }
}
