package com.example.demo.Repository;

import com.example.demo.Entity.Battle;
import com.example.demo.Entity.Enum.BattleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BattleRepository extends JpaRepository<Battle, Long> {

    @Query("SELECT b FROM Battle b " +
            "WHERE b.rpgCharacter.id = :id AND b.status = :status")
    Battle findByIdAndStatus(Long id, BattleStatus status);


    @Query("SELECT b FROM Battle b " +
            "WHERE b.status = :status")
    List<Battle> findAllBattles(BattleStatus status);
}
