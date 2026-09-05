package com.example.demo.Entity;


import com.example.demo.DTO.CharacterDTO;
import com.example.demo.Entity.Enum.CharacterClass;
import com.example.demo.Entity.Enum.WeaponType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "character")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RpgCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer level;
    private Integer hp;
    private Integer attack;
    private Integer defense;



    @OneToMany(mappedBy = "rpgCharacter")
    private List<Battle> battles = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private CharacterClass characterClass;

    @Enumerated(EnumType.STRING)
    private WeaponType weaponType;


    public void update(RpgCharacter character, CharacterDTO dto) {
        if(dto.getName() != null) {
            character.setName(dto.getName());
        }

        if(dto.getCharacterClass() != null) {
            character.setCharacterClass(dto.getCharacterClass());
            character.setWeaponType(dto.getCharacterClass().getWeaponType());
            character.setHp(dto.getCharacterClass().getDefaultHp());
            character.setAttack(dto.getCharacterClass().getDefaultAttack());
            character.setDefense(dto.getCharacterClass().getDefaultDefense());
        }
        if(dto.getHp() != null) {
            character.setHp(dto.getHp());
        }
        if(dto.getDefense() != null) {
            character.setDefense(dto.getDefense());
        }
        if(dto.getAttack() != null) {
            character.setAttack(dto.getAttack());
        }
        if(dto.getLevel() != null) {
            character.setLevel(dto.getLevel());
        }
    }
}
