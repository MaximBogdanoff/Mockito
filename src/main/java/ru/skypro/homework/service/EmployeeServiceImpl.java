package ru.skypro.homework.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.skypro.homework.exception.EmployeeAlreadyAddedException;
import ru.skypro.homework.exception.EmployeeNotFoundException;
import ru.skypro.homework.exception.EmployeeStorageIsFullException;
import ru.skypro.homework.model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees = new HashMap<>();

    @PostConstruct
    public void init() {
        employees.put("Сотрудник 1", new Employee("Вадим", "Безрукавый", 1, 50000));
        employees.put("Сотрудник 2", new Employee("Алексей", "Корников", 1, 70000));
        employees.put("Сотрудник 3", new Employee("Константин", "Реунов", 1, 90000));
        employees.put("Сотрудник 4", new Employee("Андрей", "Постов", 2, 70000));
        employees.put("Сотрудник 5", new Employee("Чугунов", "Сергей", 3, 70000));}

        @Override
        public Employee add (String firstName, String lastName,int salary, int dept){
            String key = getKey(firstName, lastName);
            if (employees.containsKey(key)) {
                throw new EmployeeAlreadyAddedException();
            }
            createEmployee(firstName, lastName);
            int EMPLOYEES_STORAGE_SIZE = 5;
            if (employees.size() == EMPLOYEES_STORAGE_SIZE) {
                throw new EmployeeStorageIsFullException("Хранилице переполнено");
            }
            Employee employee = new Employee(firstName, lastName, dept, salary);
            employees.put(key, employee);
            return employee;
        }

    private void createEmployee(String firstName, String lastName) {
    }

    @Override
        public Employee remove (String firstName, String lastName){
            String key = getKey(firstName, lastName);
            if (!employees.containsKey(key)) {
                throw new EmployeeNotFoundException("Сотрудник не найден");
            }
            createEmployee(firstName, lastName);
            return employees.remove(key);
        }

        @Override
        public Employee find (String firstName, String lastName){
            String key = getKey(firstName, lastName);
            if (!employees.containsKey(key)) {
                throw new EmployeeNotFoundException("Сотрудник не найден");
            }
            createEmployee(firstName, lastName);
            return employees.get(key);
        }

        @Override
        public List<Employee> findAll () {
            return new ArrayList<>(employees.values());
        }

        private String getKey (String firstName, String lastName){
            return firstName + lastName;
        }

        
    }
