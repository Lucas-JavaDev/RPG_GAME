package com.example.demo.Entity.Enum;

import lombok.Getter;

@Getter
public enum CharacterClass {
    WARRIOR(
            WeaponType.SWORD,
            150,
            35,
            25
    ),
    ARCHER(
            WeaponType.BOW,
            120,
            25,
            35
    ),

    ASSASIN(
            WeaponType.DAGGER,
            110,
            30,
            10
    ),

    MAGE(
            WeaponType.STAFF,
            80,
            40,
            20
    );


    private final WeaponType weaponType;
    private final Integer defaultHp;
    private final Integer defaultAttack;
    private final Integer defaultDefense;

    CharacterClass(
            WeaponType weaponType,
            Integer defaultHp,
            Integer defaultAttack,
            Integer defaultDefense
    ) {
        this.weaponType = weaponType;
        this.defaultHp = defaultHp;
        this.defaultAttack = defaultAttack;
        this.defaultDefense = defaultDefense;
    }







}
