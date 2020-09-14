package com.spring5.crud.service;

import com.spring5.crud.Repository.EmployeeRepository;
import com.spring5.crud.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService implements IEmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void create(Employee e) {
        employeeRepository.save(e);
    }

    @Override
    public Mono<Employee> findById(Integer id) {
        return Mono.justOrEmpty(employeeRepository.findById(id));
    }

    @Override
    public Flux<Employee> findAll() {
        return Flux.fromIterable(employeeRepository.findAll());
    }

    @Override
    public Mono<Employee> update(Employee e) {
        return  Mono.just(employeeRepository.save(e));
    }

    @Override
    public Mono<Void> delete(Integer id) {
        employeeRepository.deleteById(id);
        return Mono.empty();
    }
}
