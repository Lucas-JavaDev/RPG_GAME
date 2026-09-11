package com.example.demo.Entity;

import com.example.demo.Entity.Enum.BattleStatus;
import com.example.demo.Entity.Enum.BattleTurn;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "battles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Battle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "character_id")
    private RpgCharacter rpgCharacter;

    @ManyToOne
    @JoinColumn(name = "monster_id")
    private Monster monster;

    private Integer characterHp;
    private Integer monsterHp;

    private Integer characterDamage;
    private Integer monsterDamage;

    private Integer characterDefense;
    private Integer monsterDefense;

    @Enumerated(EnumType.STRING)
    private BattleTurn turn;

    @Enumerated(EnumType.STRING)
    private BattleStatus status;

}
