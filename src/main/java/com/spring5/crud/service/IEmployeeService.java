package com.spring5.crud.service;

import com.spring5.crud.model.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IEmployeeService {

    void create(Employee e);
    Mono<Employee> findById(Integer id);
    Flux<Employee> findAll();
    Mono<Employee> update(Employee e);
    Mono<Void> delete(Integer id);

}
