package com.spring5.crud.controller;

import com.spring5.crud.model.Employee;
import com.spring5.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @RequestMapping(value = { "/create" }, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void create(@RequestBody Employee e) {
        employeeService.create(e);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Flux<Employee>> findAll() {
        Flux<Employee> emps = employeeService.findAll();
        HttpStatus status = emps != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Flux<Employee>>(emps, status);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Mono<Employee>> findById(@PathVariable("id") Integer id) {
        Mono<Employee> e = employeeService.findById(id);
        HttpStatus status = e.equals(Mono.empty()) ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<Mono<Employee>>(e, status);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Mono<Employee>> update(@RequestBody Employee emp) {
        Mono<Employee> e = employeeService.findById(emp.getId());
        HttpStatus status = e.equals(Mono.empty()) ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        if(e.equals(Mono.empty())) {
            return new ResponseEntity<Mono<Employee>>(Mono.empty(), status);
        }
        return new ResponseEntity<Mono<Employee>>(employeeService.update(emp), status);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Mono<Void>> delete(@PathVariable("id") Integer id) {
        Mono<Employee> e = employeeService.findById(id);
        HttpStatus status = e.equals(Mono.empty()) ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        if(e.equals(Mono.empty())) {
            return new ResponseEntity<Mono<Void>>(Mono.empty(), status);
        }
        return new ResponseEntity<Mono<Void>>(employeeService.delete(id), status);
    }

}
