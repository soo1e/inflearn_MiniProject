package com.example.MiniProject.controller;

import com.example.MiniProject.Model.Employee;
import com.example.MiniProject.Model.Team;
import com.example.MiniProject.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/team")
    public ResponseEntity<String> registerTeam(@RequestBody Team team) {
        // Team 객체에서 팀 이름만 추출하여 전달하도록 수정
        companyService.registerTeam(team.getName());
        return ResponseEntity.ok("팀 등록에 성공했습니다!");
    }

    @PostMapping("/employee")
    public ResponseEntity<String> registerEmployee(@RequestBody Employee employee) {
        companyService.registerEmployee(employee);

        return ResponseEntity.ok("직원 등록에 성공했습니다!");
    }

    @GetMapping("/teams")
    public List<Team> getAllTeams() {
        return companyService.getAllTeams();
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return companyService.getAllEmployees();
    }
}
