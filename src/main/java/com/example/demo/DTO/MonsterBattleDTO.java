package com.example.demo.DTO;


import com.example.demo.Entity.Battle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonsterBattleDTO {

    private String name;
    private Integer defense;
    private Integer hp;
    private Integer damage;

    public MonsterBattleDTO(Battle battle) {
        name = battle.getMonster().getName();
        defense = battle.getMonsterDefense();
        hp = battle.getMonsterHp();
        damage = battle.getMonsterDamage();
    }

}
