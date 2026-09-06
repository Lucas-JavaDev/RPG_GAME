package com.example.demo.Controller;

import com.example.demo.DTO.BattleDTO;
import com.example.demo.Service.BattleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/battles")
public class BattleController {

    final BattleService battleService;

    public BattleController(BattleService battleService) {
        this.battleService = battleService;
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<BattleDTO> startBattle(@PathVariable(name = "id") Long characterId) {
        BattleDTO battleDTO = battleService.startBattle(characterId);

        return ResponseEntity.ok(battleDTO);
    }

    @PutMapping(value = "/attack/{id}")
    public ResponseEntity<BattleDTO> characterAttack(@PathVariable(name = "id") Long characterId) {
        BattleDTO battleDTO = battleService.characterAttack(characterId);
        return ResponseEntity.ok(battleDTO);
    }

}
