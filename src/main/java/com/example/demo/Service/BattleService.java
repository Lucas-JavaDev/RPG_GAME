package com.example.demo.Service;

import com.example.demo.DTO.BattleDTO;
import com.example.demo.DTO.BattleStartDTO;
import com.example.demo.DTO.BattleStatusDTO;
import com.example.demo.Entity.Battle;
import com.example.demo.Entity.Enum.BattleStatus;
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

import java.util.List;


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


    public List<BattleStatusDTO> findAllBattlesByStatus(BattleStatus status) {
        List<Battle> battles = battleRepository.findAllBattles(status);

        return battles.stream().map(battle -> new BattleStatusDTO(battle.getId(), battle.getStatus())).toList();
    }


    public BattleStartDTO startBattle(Long id) {
        RpgCharacter character = characterRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Character Not Found")
        );

        Monster monster = monsterRepository.findRandomMonster();

        Battle battle = new Battle();
        battle.setCharacterHp(character.getHp());
        battle.setCharacterDefense(character.getDefense());
        battle.setMonsterHp(monster.getHp());
        battle.setMonsterDefense(monster.getDefense());
        battle.setMonsterDamage(0);
        battle.setCharacterDamage(0);
        battle.setTurn(BattleTurn.CHARACTER);
        battle.setStatus(BattleStatus.IN_PROGRESS);
        battle.setRpgCharacter(character);
        battle.setMonster(monster);

        battleRepository.save(battle);

        return new BattleStartDTO(battle);
    }

    @Transactional
    public BattleDTO characterAttack(Long id) {
        if(!characterRepository.existsById(id)) {
            throw new ResourceNotFoundException("Character Not Found");
        }

        Battle battle = battleRepository.findByIdAndStatus(id, BattleStatus.IN_PROGRESS);
        if(battle == null) {
            throw new ResourceNotFoundException("Battle Not Found");
        }


        if(!battle.getTurn().equals(BattleTurn.CHARACTER)) {
            throw new InvalidTurnException("The current turn is Monster");
        }

        Integer damage = Math.max(battle.getRpgCharacter().getAttack() - battle.getMonsterDefense(), 0);
        battle.setCharacterDamage(damage);
        if(damage == 0) {
            if(damage == battle.getMonsterDefense()) {
                battle.setMonsterDefense(0);
            } else {
                battle.setMonsterDefense(battle.getMonsterDefense() - battle.getRpgCharacter().getAttack());
            }
        } else {
            battle.setMonsterDefense(0);
            battle.setMonsterHp(battle.getMonsterHp() - damage);
        }
        battle.setTurn(BattleTurn.MONSTER);

        if(battle.getMonsterHp() <= 0) {
            battle.setMonsterHp(0);
            battle.setStatus(BattleStatus.WINNER);
            battle.setTurn(BattleTurn.NONE);
            battleRepository.save(battle);
            return new BattleDTO(battle);
        }
        battleRepository.save(battle);

        return new BattleDTO(battle);
    }


    @Transactional
    public BattleDTO monsterAttack(Long id) {
        if(!characterRepository.existsById(id)) {
            throw new ResourceNotFoundException("Character Not Found");
        }

        Battle battle = battleRepository.findByIdAndStatus(id, BattleStatus.IN_PROGRESS);
        if(battle == null) {
            throw new ResourceNotFoundException("Battle Not Found");
        }

        if(!battle.getTurn().equals(BattleTurn.MONSTER)) {
            throw new InvalidTurnException("The current turn is Character");
        }

        Integer damage = Math.max(battle.getMonster().getDamage() - battle.getCharacterDefense(), 0);
        battle.setMonsterDamage(damage);
        if(damage == 0) {
            if(damage == battle.getCharacterDefense()) {
                battle.setCharacterDefense(0);
            } else {
                battle.setCharacterDefense(battle.getCharacterDefense() - battle.getMonster().getDamage());
            }

        } else {
            battle.setCharacterDefense(0);
            battle.setCharacterHp(battle.getCharacterHp() - damage);
        }

        battle.setTurn(BattleTurn.CHARACTER);
        if(battle.getCharacterHp() <= 0) {
            battle.setCharacterHp(0);
            battle.setStatus(BattleStatus.DEFEAT);
            battle.setTurn(BattleTurn.NONE);
            battleRepository.save(battle);
            return new BattleDTO(battle);
        }

        battleRepository.save(battle);
        return new BattleDTO(battle);
    }




}
