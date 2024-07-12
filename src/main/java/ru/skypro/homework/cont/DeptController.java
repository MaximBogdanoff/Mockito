package ru.skypro.homework.cont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.model.Employee;
import ru.skypro.homework.service.DeptService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dept")
public class DeptController {

    private final DeptService service;
    @Autowired
    public DeptController(DeptService service) {
        this.service = service;
    }

    @GetMapping("{id}/min-salary")
    public Employee findEmployeeWithMinSalaryFromDept(@PathVariable("id") int dept) {
        return service.findEmployeeWithMinSalaryFromDept(dept);
    }

    @GetMapping("{id}/max-salary")
    public Employee findEmployeeWithMaxSalaryFromDept(@PathVariable("id") int dept) {
        return service.findEmployeeWithMaxSalaryFromDept(dept);
    }


    @GetMapping(path = "{id}/all", params = "{id}/deptId")
    public List<Employee> getAllEmployeesGroupedByDept(@PathVariable("id") int deptId) {
        return service.getAll(deptId);
    }

    @GetMapping("{id}/all")
    public Map<Integer, List<Employee>> getAllEmployeesGroupedByDept() {
        return service.getAllGroupedByDept();
    }
    @GetMapping("{id}salary/sum")
    public Integer getSalarySum(@PathVariable("id") Integer id) {
        return service.getSalary(id);
    }
}