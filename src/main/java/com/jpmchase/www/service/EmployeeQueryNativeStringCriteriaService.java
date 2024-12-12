package com.jpmchase.www.service;

import com.jpmchase.www.modal.Employee;
import org.springframework.data.elasticsearch.core.SearchHits;

public interface EmployeeQueryNativeStringCriteriaService {

    SearchHits<Employee> searchByNameUsingCriteria(String firstName);

    SearchHits<Employee> searchByNameUsingNativeQuery(String firstName);

    SearchHits<Employee> searchByNameUsingQuery(String firstName);
}
