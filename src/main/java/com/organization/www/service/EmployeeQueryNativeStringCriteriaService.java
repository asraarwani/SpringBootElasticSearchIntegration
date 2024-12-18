package com.organization.www.service;

import com.organization.www.modal.Employee;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.List;

public interface EmployeeQueryNativeStringCriteriaService {

    SearchHits<Employee> searchByNameUsingCriteria(String firstName);

    SearchHits<Employee> searchByNameUsingNativeQuery(String firstName);

    SearchHits<Employee> searchByNameUsingQuery(String firstName);

    List<String> bulkUpload(List<Employee> list);
}
