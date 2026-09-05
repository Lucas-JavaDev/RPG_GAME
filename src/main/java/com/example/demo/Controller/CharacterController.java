package com.example.demo.Controller;


import com.example.demo.DTO.CharacterDTO;
import com.example.demo.Service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/characters")
public class CharacterController {

    @Autowired
    CharacterService characterService;

    @GetMapping(value = "/{characterId}")
    public ResponseEntity<CharacterDTO> findById(@PathVariable(name = "characterId") Long id) {
        CharacterDTO dto = characterService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<CharacterDTO>> findAll() {
        List<CharacterDTO> characters = characterService.findAll();
        return ResponseEntity.ok(characters);
    }

    @PostMapping
    public ResponseEntity<CharacterDTO> create(@RequestBody CharacterDTO characterDTO) {
        characterDTO = characterService.create(characterDTO.getName(), characterDTO.getCharacterClass().toString());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(characterDTO)
                .toUri();

        return ResponseEntity.created(uri).body(characterDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CharacterDTO> update(@RequestBody CharacterDTO characterDTO, @PathVariable Long id) {
        return ResponseEntity.ok(characterService.update(characterDTO, id));
    }
}
