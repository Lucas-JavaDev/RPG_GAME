package com.example.demo.Repository;

import com.example.demo.Entity.RpgCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<RpgCharacter, Long> {
}
