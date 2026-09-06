package com.example.demo.Service;

import com.example.demo.DTO.BattleDTO;
import com.example.demo.Entity.Battle;
import com.example.demo.Entity.Enum.BattleResult;
import com.example.demo.Entity.Enum.BattleTurn;
import com.example.demo.Entity.Monster;
import com.example.demo.Entity.RpgCharacter;
import com.example.demo.Exception.InvalidTurnException;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repository.BattleRepository;
import com.example.demo.Repository.CharacterRepository;
import com.example.demo.Repository.MonsterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BattleService {

    final
    BattleRepository battleRepository;

    final
    CharacterRepository characterRepository;

    final MonsterRepository monsterRepository;

    public BattleService(BattleRepository battleRepository, CharacterRepository characterRepository, MonsterRepository monsterRepository) {
        this.battleRepository = battleRepository;
        this.characterRepository = characterRepository;
        this.monsterRepository = monsterRepository;
    }


    public BattleDTO startBattle(Long id) {
        RpgCharacter character = characterRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Character Not Found")
        );

        Monster monster = monsterRepository.findRandomMonster();

        Battle battle = new Battle();
        battle.setCharacterHp(character.getHp());
        battle.setCharacterDamage(0);
        battle.setCharacterDefense(character.getDefense());
        battle.setMonsterHp(monster.getHp());
        battle.setMonsterDamage(0);
        battle.setMonsterDefense(monster.getDefense());
        battle.setTurn(BattleTurn.CHARACTER);
        battle.setResult(BattleResult.IN_PROGRESS);
        battle.setRpgCharacter(character);
        battle.setMonster(monster);

        battleRepository.save(battle);

        return new BattleDTO(battle);
    }

    @Transactional
    public BattleDTO characterAttack(Long id) {
        if(!characterRepository.existsById(id)) {
            throw new ResourceNotFoundException("Character Not Found");
        }
        RpgCharacter character = characterRepository.getReferenceById(id);

        Battle battle = battleRepository.findByIdAndStatus(id, BattleResult.IN_PROGRESS);
        if(battle == null) {
            throw new ResourceNotFoundException("Battle Not Found");
        }

        if(!battle.getTurn().equals(BattleTurn.CHARACTER)) {
            throw new InvalidTurnException("The current turn is Monster");
        }

        Integer damage = Math.max(0, character.getAttack() - battle.getMonster().getDefense());
        battle.setCharacterDamage(damage);
        if(damage == 0) {
            battle.setMonsterDefense(battle.getMonsterDefense() - character.getAttack());
        } else {
            battle.setMonsterHp(battle.getMonsterHp() - damage);
        }

        battleRepository.save(battle);

        return new BattleDTO(battle);

    }


}
