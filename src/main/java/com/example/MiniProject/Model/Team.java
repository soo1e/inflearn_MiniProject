package com.example.MiniProject.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String manager;

    private int memberCount;

    // 직원 리스트
    @Transient
    private List<Employee> employees = new ArrayList<>();

    public Team() {}

    public Team(String name) {
        this.name = name;
        this.memberCount = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    // 직원 추가 메서드
    public void addEmployee(Employee employee) {
        employees.add(employee);
        if ("MANAGER".equals(employee.getRole())) {
            this.manager = employee.getName();
        }
        incrementMemberCount(); // 이 부분에서 직접 메서드 호출을 변경합니다.
    }

    // 직원 삭제 메서드
    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        if ("MANAGER".equals(employee.getRole())) {
            this.manager = null;
        }
        decrementMemberCount(); // 이 부분에서 직접 메서드 호출을 변경합니다.
    }

    // 멤버 수 증가 메서드
    public void incrementMemberCount() {
        this.memberCount++;
    }

    // 멤버 수 감소 메서드
    public void decrementMemberCount() {
        this.memberCount--;
    }

    @Override
    public String toString() {
        return "{" +
                "\"name\": \"" + name + "\"," +
                "\"manager\": \"" + manager + "\"," +
                "\"memberCount\": " + memberCount +
                "}";
    }

    // 새로운 메서드 추가 - 직원 수 반환
    public int getMemberCount() {
        return memberCount;
    }


}
