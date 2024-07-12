package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.model.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class DeptServiceImpl implements DeptService{

    private final EmployeeService employeeService;

    public DeptServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @Override
    public Employee findEmployeeWithMinSalaryFromDept(int dept) {
        return employeeService.findAll()
                .stream()
                .filter(employee -> employee.getDept() == dept)
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow();
    }
    @Override
    public Employee findEmployeeWithMaxSalaryFromDept(int dept) {
        return employeeService.findAll()
                .stream()
                .filter(employee -> employee.getDept() == dept)
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow();
    }

    @Override
    public List<Employee> getAll(int dept) {
        return employeeService.findAll()
                .stream()
                .filter(employee -> employee.getDept() == dept)
                .collect(Collectors.toList());
    }


    @Override
    public Map<Integer, List<Employee>> getAllGroupedByDept() {
        return employeeService.findAll()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDept));
    }

    @Override
    public Integer getSalary(Integer deptId) {
        return employeeService.findAll()
                .stream()
                .filter(employee -> employee.getDept() == deptId)
                .map(Employee::getSalary)
                .mapToInt(Integer::intValue)
                .sum();
    }
}