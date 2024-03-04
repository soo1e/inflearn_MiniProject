package com.example.MiniProject.Repository;

import com.example.MiniProject.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByTeamNameAndRole(String teamName, String role);
}