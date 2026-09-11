package com.example.demo.DTO;


import com.example.demo.Entity.Battle;
import com.example.demo.Entity.Enum.BattleStatus;
import com.example.demo.Entity.Enum.BattleTurn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BattleStartDTO {


    private Long battleId;
    private String characterName;
    private Integer characterHp;
    private String monsterName;
    private Integer monsterHp;
    private BattleTurn turn;
    private BattleStatus status;

    public BattleStartDTO(Battle battle) {
        battleId = battle.getId();
        characterName = battle.getRpgCharacter().getName();
        characterHp = battle.getCharacterHp();
        monsterName = battle.getMonster().getName();
        monsterHp = battle.getMonsterHp();
        turn = battle.getTurn();
        status = battle.getStatus();
    }

}
