package com.organization.www.controller;

import com.organization.www.modal.Employee;
import com.organization.www.service.EmployeeOperationsService;
import com.organization.www.service.EmployeeQueryNativeStringCriteriaService;
import com.organization.www.service.EmployeeService;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    final private EmployeeService employeeService;
    final private EmployeeOperationsService employeeOperationsService;
    final private EmployeeQueryNativeStringCriteriaService service;

    public EmployeeController(EmployeeService employeeService, EmployeeOperationsService employeeOperationsService, EmployeeQueryNativeStringCriteriaService service) {
        this.employeeService = employeeService;
        this.employeeOperationsService = employeeOperationsService;
        this.service = service;
    }

    @GetMapping(value = "/{name}", produces = "application/json")
    public ResponseEntity<List<Employee>> findByFirstName(@PathVariable("name") String name) {
        List<Employee> employeeList = employeeService.findByFirstName(name);
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Integer> saveEmployee(@RequestBody Employee employee) {
        Integer employeeId = employeeOperationsService.saveEmployee(employee);
        return new ResponseEntity<>(employeeId, HttpStatus.OK);
    }

    @PostMapping("/save-all")
    public ResponseEntity<List<Integer>> saveEmployeeInBulk(@RequestBody List<Employee> employeeList) {
        List<Integer> employeeIds = employeeOperationsService.saveEmployeeInBulk(employeeList);
        return new ResponseEntity<>(employeeIds, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Employee> findEmployeeById(@RequestParam String id) {
        Employee employee = employeeOperationsService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteEmployeeById(@RequestParam String id) {
        String employeeId = employeeOperationsService.deleteEmployeeById(id);
        return new ResponseEntity<>(employeeId, HttpStatus.OK);
    }

    @GetMapping("/criteria")
    public ResponseEntity<SearchHits<Employee>> searchByNameUsingCriteria(@RequestParam String firstName) {
        SearchHits<Employee> searchHits = service.searchByNameUsingCriteria(firstName);
        return new ResponseEntity<>(searchHits, HttpStatus.OK);
    }

    @GetMapping("/native-query")
    public ResponseEntity<SearchHits<Employee>> searchByNameUsingNativeQuery(@RequestParam String firstName) {
        SearchHits<Employee> searchHits = service.searchByNameUsingNativeQuery(firstName);
        return new ResponseEntity<>(searchHits, HttpStatus.OK);
    }

    @GetMapping("/query")
    public ResponseEntity<SearchHits<Employee>> searchByNameUsingQuery(@RequestParam String firstName) {
        SearchHits<Employee> searchHits = service.searchByNameUsingQuery(firstName);
        return new ResponseEntity<>(searchHits, HttpStatus.OK);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<String>> bulkUpload(@RequestBody List<Employee> list) {
        List<String> idList = service.bulkUpload(list);
        return new ResponseEntity<>(idList, HttpStatus.OK);
    }
}

