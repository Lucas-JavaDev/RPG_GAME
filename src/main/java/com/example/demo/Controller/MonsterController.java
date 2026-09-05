package com.example.demo.Controller;


import com.example.demo.DTO.MonsterDTO;
import com.example.demo.Service.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/monsters")
public class MonsterController {

    @Autowired
    MonsterService monsterService;

    @GetMapping
    public ResponseEntity<List<MonsterDTO>> findAll() {
        return ResponseEntity.ok(monsterService.findAll());
    }

    @GetMapping(value = "/{monsterId}")
    public ResponseEntity<MonsterDTO> findById(@PathVariable(name = "monsterId") Long id) {
        return ResponseEntity.ok(monsterService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MonsterDTO> create(@RequestBody MonsterDTO monsterDTO) {
        monsterDTO = monsterService.create(monsterDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(monsterDTO).toUri();

        return ResponseEntity.created(uri).body(monsterDTO);
    }


    @PutMapping(value = "/{monsterId}")
    public ResponseEntity<MonsterDTO> update(@RequestBody MonsterDTO monsterDTO, @PathVariable(name = "monsterId") Long id) {
        monsterDTO = monsterService.update(monsterDTO, id);
        return ResponseEntity.ok(monsterDTO);
    }


}
