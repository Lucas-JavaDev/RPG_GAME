package com.example.demo.DTO;

import com.example.demo.Entity.RpgCharacter;
import com.example.demo.Entity.Enum.CharacterClass;
import com.example.demo.Entity.Enum.WeaponType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDTO {

    private Long id;
    private String name;
    private Integer level;
    private Integer hp;
    private Integer defense;
    private Integer attack;
    private CharacterClass characterClass;
    private WeaponType weaponType;

    public CharacterDTO(RpgCharacter rpgCharacter) {
        id = rpgCharacter.getId();
        name  = rpgCharacter.getName();
        level = rpgCharacter.getLevel();
        hp = rpgCharacter.getHp();
        defense = rpgCharacter.getDefense();
        attack = rpgCharacter.getAttack();
        characterClass = rpgCharacter.getCharacterClass();
        weaponType = rpgCharacter.getWeaponType();
    }
}

