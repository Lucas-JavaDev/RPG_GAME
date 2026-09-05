package com.example.demo.Entity;

import com.example.demo.Entity.Enum.WeaponType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "weapon")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Weapon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer damage;
    private Integer level;

    @Enumerated(EnumType.STRING)
    private WeaponType type;


}
