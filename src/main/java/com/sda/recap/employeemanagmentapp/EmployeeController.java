package com.sda.recap.employeemanagmentapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    //Field injection
    @Autowired
    EmployeeRepository employeeRepository;


    @GetMapping("/employees")
    public List<Employee> getEmployeesList(){
        return employeeRepository.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable Long id){
        return employeeRepository.findById(id).get();
    }

    @DeleteMapping("/employees/{id}")
    public void deleteTodo(@PathVariable Long id){
        employeeRepository.deleteById(id);

    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable long id, @RequestBody Employee upEmployee){

          return employeeRepository.findById(id).map(employee -> {
                employee.setFirstName(upEmployee.getFirstName());
                employee.setLastName(upEmployee.getLastName());
                employee.setEmail(upEmployee.getEmail());
                return employeeRepository.save(employee);
            }).orElseGet(() -> {
              upEmployee.setId(id);
                return employeeRepository.save(upEmployee);
            });


    }

    @PostMapping("/employees")
    public void createEmployee(@RequestBody Employee employee){
        employeeRepository.save(employee);

    }

}
