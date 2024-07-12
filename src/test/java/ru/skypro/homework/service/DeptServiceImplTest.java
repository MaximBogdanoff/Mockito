package ru.skypro.homework.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.homework.model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class DeptServiceImplTest {
    private final List<Employee> employeeList = new ArrayList<>() {{
        add(new Employee("Вадим", "Безрукавый", 1, 50000));
        add(new Employee("Алексей", "Корников", 1, 70000));
        add(new Employee("Константин", "Реунов", 1, 90000));
        add(new Employee("Андрей", "Постов", 2, 50000));
        add(new Employee("Чугунов", "Сергей", 3, 50000));
    }};
    private final Map<String, Employee> employees = new HashMap<>();

    @BeforeEach
    public void initMap() {
        for (Employee employee : employeeList) {
            employees.put(employee.getFirstName() + employee.getLastName(), employee);
        }
    }

    @Mock
    private EmployeeServiceImpl employeeService;
    @InjectMocks
    private DeptServiceImpl deptService;

    @Test
    public void shouldGetAll() {
        int deptId = 1;
        Mockito.when(employeeService.findAll()).thenReturn(employeeList);
        List<Employee> expectedEmployees = List.of(employeeList.get(0), employeeList.get(1), employeeList.get(2));
        List<Employee> actualEmployees = deptService.getAll(deptId);
        Assertions.assertThat(actualEmployees)
                .containsExactlyInAnyOrderElementsOf(expectedEmployees);
    }

    @Test
    public void shouldGetSalarySum() {
        int deptId = 1;
        Mockito.when(employeeService.findAll()).thenReturn(employeeList);
        Integer expectedSalary = employeeList.get(0).getSalary() + employeeList.get(1).getSalary() + employeeList.get(2).getSalary();
        Integer actualSalary = deptService.getSalary(deptId);
        Assertions.assertThat(actualSalary)
                .isEqualTo(expectedSalary);
    }

    @Test
    public void shouldFindEmployeeWithMinSalaryFromDept() {
        int deptId = 1;
        Mockito.when(employeeService.findAll()).thenReturn(employeeList);
        Employee expectedEmployeesWithMinSalaryFromDept = employeeList.get(0);
        Employee actualEmployeesWithMinSalary = deptService.findEmployeeWithMinSalaryFromDept(deptId);
        Assertions
                .assertThat(actualEmployeesWithMinSalary).isEqualTo(expectedEmployeesWithMinSalaryFromDept);
    }

    @Test
    public void shouldFindEmployeeWithMaxSalaryFromDept() {
        int deptId = 1;
        Mockito.when(employeeService.findAll()).thenReturn(employeeList);
        Employee expectedEmployeesWithMaxSalaryFromDept = employeeList.get(2);
        Employee actualEmployeesWithMaxSalary = deptService.findEmployeeWithMaxSalaryFromDept(deptId);
        Assertions.assertThat(actualEmployeesWithMaxSalary)
                .isEqualTo(expectedEmployeesWithMaxSalaryFromDept);
    }

    @Test
    public void shouldGetAllGroupedByDept() {
        Map<Integer, List<Employee>> expectedAllGroupedByDept = new HashMap<>() {{
            put(1, List.of(employeeList.get(0), employeeList.get(1), employeeList.get(2)));
            put(2, List.of(employeeList.get(3)));
            put(3, List.of(employeeList.get(4)));
        }};
        Mockito.when(employeeService.findAll()).thenReturn(employeeList);
        Map<Integer, List<Employee>> actualAllGroupedByDept = deptService.getAllGroupedByDept();
        Assertions.assertThat(actualAllGroupedByDept).isEqualTo(expectedAllGroupedByDept);
    }
}