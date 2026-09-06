package com.example.demo.Repository;

import com.example.demo.Entity.Battle;
import com.example.demo.Entity.Enum.BattleResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BattleRepository extends JpaRepository<Battle, Long> {

    @Query("SELECT b FROM Battle b " +
            "WHERE b.rpgCharacter.id = :id AND b.result = :status")
    Battle findByIdAndStatus(Long id, BattleResult status);
}
