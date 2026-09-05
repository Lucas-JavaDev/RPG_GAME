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
                rpgCharacter.getHp(),
                rpgCharacter.getDefense(),
                rpgCharacter.getAttack(),
                rpgCharacter.getCharacterClass(),
                rpgCharacter.getWeaponType()
        );
    }

    public List<CharacterDTO> findAll() {
        List<RpgCharacter> characters = characterRepository.findAll();
        return characters.stream().map(character -> new CharacterDTO(character)).toList();
    }

    @Transactional
    public CharacterDTO create(CharacterDTO characterDTO) {
        RpgCharacter character = new RpgCharacter();

        CharacterClass characterType = CharacterClass.valueOf(characterDTO.getCharacterClass().name().toUpperCase());

        character.setName(characterDTO.getName());
        character.setLevel(1);
        character.setCharacterClass(characterType);
        character.setHp(characterType.getDefaultHp());
        character.setAttack(characterType.getDefaultAttack());
        character.setDefense(characterType.getDefaultDefense());
        character.setWeaponType(characterType.getWeaponType());

        characterRepository.save(character);

        return new CharacterDTO(character);
    }


    @Transactional
    public CharacterDTO update(CharacterDTO dto, Long id) {

        RpgCharacter character = characterRepository.getReferenceById(id);

        if(!characterRepository.existsById(id)) {
            throw new ResourceNotFoundException("Character not found");
        }
        character.update(character, dto);

        characterRepository.save(character);

        return new CharacterDTO(character);
    }

}
