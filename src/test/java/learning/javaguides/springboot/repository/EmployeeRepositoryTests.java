package learning.javaguides.springboot.repository;

import learning.javaguides.springboot.model.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    EmployeeRepository employeeRepository;

    //JUnit test for save employee operation
    @DisplayName("JUnit test for save employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnedSavedEmployee() {

        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Devang")
                .lastName("Chauhan")
                .email("devang@gmail.com")
                .build();

        // when - action or the behaviour that we are going to test
        Employee savedEmployee = employeeRepository.save(employee);

        //then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    //JUnit test for get all employees operation
    @DisplayName("JUnit test for get all employees operation")
    @Test
    public void givenEmployeesList_whenFindAll_thenEmployeesList() {

        // given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Devang")
                .lastName("Chauhan")
                .email("devang@gmail.com")
                .build();

        Employee employee1 = Employee.builder()
                .firstName("John")
                .lastName("Cena")
                .email("cena@gmail.com")
                .build();

        employeeRepository.save(employee);
        employeeRepository.save(employee1);

        // when - action or the behaviour that we are going to test
        List<Employee> employees = employeeRepository.findAll();

        // then - verify the output
        assertThat(employees).isNotNull();
        assertThat(employees).size().isEqualTo(2);
    }

    //JUnit test for get employee by id operation
    @DisplayName("JUnit test for get employee by id operation")
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {

        // given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Devang")
                .lastName("Chauhan")
                .email("devang@gmail.com")
                .build();

        employeeRepository.save(employee);

        // when - action or the behaviour that we are going to test
        Employee employeeDB = employeeRepository.findById(employee.getId()).get();

        // then - verify the output
        assertThat(employeeDB).isNotNull();
    }

    //JUnit test for get employee by email operation
    @DisplayName("JUnit test for get employee by email operation ")
    @Test
    public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject() {

        // given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Snehal")
                .lastName("Chauhan")
                .email("snehal@gmail.com")
                .build();

        employeeRepository.save(employee);

        // when - action or the behaviour that we are going to test
        Employee employeeDB = employeeRepository.findByEmail(employee.getEmail()).get();

        // then - verify the output
        assertThat(employeeDB).isNotNull();
    }

    //JUnit test for update employee operation
    @DisplayName("JUnit test for update employee operation")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {

        // given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Snehal")
                .lastName("Chauhan")
                .email("snehal@gmail.com")
                .build();

        employeeRepository.save(employee);

        // when - action or the behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
        savedEmployee.setEmail("snehal84@gmail.com");
        Employee updatedEmployee = employeeRepository.save(savedEmployee);

        // then - verify the output
        assertThat(updatedEmployee.getEmail()).isEqualTo("snehal84@gmail.com");
    }

    //JUnit test for delete employee operation
    @DisplayName("JUnit test for delete employee operation")
    @Test
    public void givenEmployeeObject_whenDelete_thenRemoveEmployee() {

        // given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Devang")
                .lastName("Chauhan")
                .email("snehal@gmail.com")
                .build();

        employeeRepository.save(employee);

        // when - action or the behaviour that we are going to test
        employeeRepository.deleteById(employee.getId());
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

        // then - verify the output
        assertThat(employeeOptional).isEmpty();
    }

}
