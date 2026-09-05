package com.example.demo.DTO;


import com.example.demo.Entity.Monster;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonsterDTO {

    private Long id;
    private String name;
    private Integer level;
    private Integer damage;
    private Integer hp;
    private Integer defense;

    public MonsterDTO(Monster monster) {
        id = monster.getId();
        name = monster.getName();
        level = monster.getLevel();
        damage = monster.getDamage();
        hp = monster.getHp();
        defense = monster.getDefense();
    }


}
