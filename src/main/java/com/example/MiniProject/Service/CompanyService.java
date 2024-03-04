package com.example.MiniProject.Service;

import com.example.MiniProject.Model.Employee;
import com.example.MiniProject.Model.Team;
import com.example.MiniProject.Repository.EmployeeRepository;
import com.example.MiniProject.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final TeamRepository teamRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public CompanyService(TeamRepository teamRepository, EmployeeRepository employeeRepository) {
        this.teamRepository = teamRepository;
        this.employeeRepository = employeeRepository;
    }

    // 팀 등록 메소드
    public Team registerTeam(String teamName) {
        Team team = new Team();
        team.setName(teamName);
        return teamRepository.save(team);
    }


    // 모든 팀 조회 메소드
    public List<Team> getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        for (Team team : teams) {
            String managerName = getManagerName(team.getName());
            team.setManager(managerName);
        }
        return teams;
    }

    // 모든 직원 조회 메소드
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // 팀의 직원 수 반환 메소드
    public int getMemberCount(String teamName) {
        Team team = teamRepository.findByName(teamName);
        if (team != null) {
            return team.getMemberCount();
        }
        return 0;
    }

    // 팀의 매니저 이름 반환 메소드
    private String getManagerName(String teamName) {
        Team team = teamRepository.findByName(teamName);
        if (team != null) {
            List<Employee> managers = employeeRepository.findByTeamNameAndRole(teamName, "MANAGER");
            if (!managers.isEmpty()) {
                // 첫 번째 매니저의 이름 반환
                return managers.get(0).getName();
            }
        }
        return null;
    }

    public Employee registerEmployee(Employee employee) {
        Team team = teamRepository.findByName(employee.getTeamName());
        if (team != null) {
            List<Employee> managers = employeeRepository.findByTeamNameAndRole(employee.getTeamName(), "MANAGER");
            if (!managers.isEmpty()) {
                // 매니저가 존재할 경우 매니저의 이름을 팀의 매니저로 설정
                team.setManager(managers.get(0).getName());
            }
            team.incrementMemberCount();
            teamRepository.save(team);
        }
        return employeeRepository.save(employee);
    }

}
