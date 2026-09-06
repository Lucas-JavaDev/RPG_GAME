package com.example.demo.Repository;

import com.example.demo.Entity.Monster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MonsterRepository extends JpaRepository<Monster, Long> {


    @Query(nativeQuery = true, value = "SELECT * FROM monster ORDER BY random() LIMIT 1")
    public Monster findRandomMonster();

}
