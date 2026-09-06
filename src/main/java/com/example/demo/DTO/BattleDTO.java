package com.example.demo.DTO;


import com.example.demo.Entity.Battle;
import com.example.demo.Entity.Enum.BattleResult;
import com.example.demo.Entity.Enum.BattleTurn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BattleDTO {

    private Long battleId;

    private String characterName;
    private Integer characterHp;
    private Integer characterDamage;
    private Integer characterDefense;

    private String monsterName;
    private Integer monsterHp;
    private Integer monsterDamage;
    private Integer monsterDefense;



    private BattleTurn turn;
    private BattleResult status;

    public BattleDTO(Battle battle) {
        battleId = battle.getId();
        characterName = battle.getRpgCharacter().getName();
        characterHp = battle.getCharacterHp();
        characterDamage = battle.getCharacterDamage();
        characterDefense = battle.getCharacterDefense();
        monsterName = battle.getMonster().getName();
        monsterHp = battle.getMonsterHp();
        monsterDamage = battle.getMonsterDamage();
        monsterDefense = battle.getMonsterDefense();

        turn = battle.getTurn();
        status = battle.getResult();
    }

}
