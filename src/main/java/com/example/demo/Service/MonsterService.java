package com.example.demo.Service;


import com.example.demo.DTO.MonsterDTO;
import com.example.demo.Entity.Monster;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repository.MonsterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonsterService {

    @Autowired
    MonsterRepository monsterRepository;

    public MonsterDTO create(MonsterDTO monsterDTO) {
        Monster monster = new Monster();

        setValues(monster, monsterDTO);
        monsterRepository.save(monster);

        return new MonsterDTO(monster);
    }

    public MonsterDTO update(MonsterDTO monsterDTO, Long id) {
        Monster monster = monsterRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Monster Not Found")
        );
        monster.update(monster, monsterDTO);
        monsterRepository.save(monster);

        return new MonsterDTO(monster);
    }

    public MonsterDTO findById(Long id) {
        Monster monster = monsterRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Monster Not Found")
        );

        return new MonsterDTO(
                monster.getId(),
                monster.getName(),
                monster.getLevel(),
                monster.getDamage(),
                monster.getHp(),
                monster.getDefense()
        );
    }

    public List<MonsterDTO> findAll() {
        List<Monster> monsters = monsterRepository.findAll();
        return monsters.stream().map(monster -> new MonsterDTO(monster)).toList();
    }

    public void setValues(Monster monster, MonsterDTO monsterDTO) {
        monster.setName(monsterDTO.getName());
        monster.setHp(monsterDTO.getHp());
        monster.setLevel(1);
        monster.setDamage(monsterDTO.getDamage());
        monster.setDefense(monsterDTO.getDefense());
    }
}
