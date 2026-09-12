package com.example.demo.DTO;

import com.example.demo.Entity.Battle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterBattleDTO {

    private String name;
    private Integer defense;
    private Integer hp;
    private Integer damage;

    public CharacterBattleDTO(Battle battle) {
        name = battle.getRpgCharacter().getName();
        defense = battle.getCharacterDefense();
        hp = battle.getCharacterHp();
        damage = battle.getCharacterDamage();
    }
}
