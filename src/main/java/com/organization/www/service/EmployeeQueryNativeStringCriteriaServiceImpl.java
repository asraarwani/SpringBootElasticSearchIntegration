package com.organization.www.service;

import com.organization.www.modal.Employee;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;

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
}
