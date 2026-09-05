package com.example.demo.Service;

import com.example.demo.Repository.BattleRepository;
import com.example.demo.Repository.CharacterRepository;
import org.springframework.stereotype.Service;


@Service
public class BattleService {

    final
    BattleRepository battleRepository;

    final
    CharacterRepository characterRepository;

    public BattleService(BattleRepository battleRepository, CharacterRepository characterRepository) {
        this.battleRepository = battleRepository;
        this.characterRepository = characterRepository;
    }

}
