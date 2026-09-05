package com.example.demo.Entity;


import com.example.demo.DTO.MonsterDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "monster")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Monster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer level;
    private Integer damage;
    private Integer hp;
    private Integer defense;

    @OneToMany(mappedBy = "monster")
    private List<Battle> battles = new ArrayList<>();


    public void update(Monster monster, MonsterDTO monsterDTO) {
        if(monsterDTO.getName() != null) {
            monster.setName(monsterDTO.getName());
        }
        if(monsterDTO.getHp() != null) {
            monster.setHp(monsterDTO.getHp());
        }
        if(monsterDTO.getDamage() != null) {
            monster.setDamage(monsterDTO.getDamage());
        }
        if(monsterDTO.getDefense() != null) {
            monster.setDefense(monsterDTO.getDefense());
        }
    }
}
