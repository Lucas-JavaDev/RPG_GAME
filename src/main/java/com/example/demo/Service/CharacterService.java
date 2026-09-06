package com.example.demo.Service;


import com.example.demo.DTO.CharacterDTO;
import com.example.demo.Entity.Enum.CharacterClass;
import com.example.demo.Entity.RpgCharacter;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CharacterService {

    @Autowired
    CharacterRepository characterRepository;


    @Transactional(readOnly = true)
    public CharacterDTO findById(Long id) {

        RpgCharacter rpgCharacter = characterRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Character Not Found")
        );

        return new CharacterDTO(
                rpgCharacter.getId(),
                rpgCharacter.getName(),
                rpgCharacter.getLevel(),
                rpgCharacter.getXp(),
                rpgCharacter.getHp(),
                rpgCharacter.getDefense(),
                rpgCharacter.getAttack(),
                rpgCharacter.getCharacterClass(),
                rpgCharacter.getWeaponType()
        );
    }

    public RpgCharacter findEntityById(Long id) {
        return characterRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Character Not Found")
        );
    }

    public List<CharacterDTO> findAll() {
        List<RpgCharacter> characters = characterRepository.findAll();
        return characters.stream().map(character -> new CharacterDTO(character)).toList();
    }

    @Transactional
    public CharacterDTO create(CharacterDTO characterDTO) {
        RpgCharacter character = new RpgCharacter();

        setValues(character, characterDTO);
        return new CharacterDTO(character);
    }


    @Transactional
    public CharacterDTO update(CharacterDTO dto, Long id) {

        RpgCharacter character = characterRepository.getReferenceById(id);

        if(!characterRepository.existsById(id)) {
            throw new ResourceNotFoundException("Character not found");
        }
        update(character, dto);
        return new CharacterDTO(character);
    }


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
        characterRepository.save(character);
    }

    public void setValues(RpgCharacter character, CharacterDTO characterDTO) {
        CharacterClass characterType = CharacterClass.valueOf(characterDTO.getCharacterClass().name().toUpperCase());
        character.setName(characterDTO.getName());
        character.setLevel(1);
        character.setXp(0);
        character.setCharacterClass(characterType);
        character.setHp(characterType.getDefaultHp());
        character.setAttack(characterType.getDefaultAttack());
        character.setDefense(characterType.getDefaultDefense());
        character.setWeaponType(characterType.getWeaponType());

        characterRepository.save(character);
    }


    public Integer calculateXpNeeded(Integer level) {
        return 50 * level + 35 * (level - 1);
    }

    @Transactional
    public void gainXp(RpgCharacter character, Integer xpGained) {
        character.setXp(character.getXp() + xpGained);


        while(character.getXp() >= calculateXpNeeded(character.getLevel())) {
            character.setXp(character.getXp() - calculateXpNeeded(character.getLevel()));

            levelUp(character);
        }
        characterRepository.save(character);
    }

    public Integer calculateAttribute(Integer baseAttribute, Integer level) {
        return (int) Math.round(
                baseAttribute * (1 + 0.10 * (level - 1))
        );
    }


    @Transactional
    public void levelUp(RpgCharacter character) {

        character.setLevel(character.getLevel() + 1);

        Integer attack = calculateAttribute(character.getCharacterClass().getDefaultAttack(), character.getLevel());
        Integer hp = calculateAttribute(character.getCharacterClass().getDefaultHp(), character.getLevel());
        Integer defense = calculateAttribute(character.getCharacterClass().getDefaultDefense(), character.getLevel());

        character.setAttack(attack);
        character.setHp(hp);
        character.setDefense(defense);

    }

}
