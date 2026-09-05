package com.example.demo.Entity;


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

}
