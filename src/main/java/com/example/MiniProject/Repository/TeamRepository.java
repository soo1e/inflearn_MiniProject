package com.example.MiniProject.Repository;

import com.example.MiniProject.Model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Team findByName(String name); // teamName -> name으로 수정

    @Modifying
    @Transactional
    @Query("UPDATE Team t SET t.memberCount = t.memberCount + 1 WHERE t.name = :name") // teamName -> name으로 수정
    void incrementMemberCount(String name);

    @Modifying
    @Transactional
    @Query("UPDATE Team t SET t.memberCount = t.memberCount - 1 WHERE t.name = :name") // teamName -> name으로 수정
    void decrementMemberCount(String name);
}
