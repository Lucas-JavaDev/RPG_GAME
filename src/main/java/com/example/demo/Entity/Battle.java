package com.example.demo.Entity;

import com.example.demo.Entity.Enum.BattleResult;
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


    @Enumerated(EnumType.STRING)
    private BattleResult result;

}
