package com.example.demo.Controller;

import com.example.demo.DTO.BattleDTO;
import com.example.demo.DTO.BattleStartDTO;
import com.example.demo.DTO.BattleStatusDTO;
import com.example.demo.Entity.Enum.BattleStatus;
import com.example.demo.Service.BattleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/battles")
public class BattleController {

    final BattleService battleService;

    public BattleController(BattleService battleService) {
        this.battleService = battleService;
    }


    @GetMapping
    public ResponseEntity<List<BattleStatusDTO>> findAllActivesBattles(@RequestParam String status) {
        List<BattleStatusDTO> battleDTO = battleService.findAllBattlesByStatus(BattleStatus.valueOf(status));
        return ResponseEntity.ok(battleDTO);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<BattleStartDTO> startBattle(@PathVariable(name = "id") Long characterId) {
        BattleStartDTO battleStartDTO = battleService.startBattle(characterId);

        return ResponseEntity.ok(battleStartDTO);
    }

    @PutMapping(value = "/attack/{id}")
    public ResponseEntity<BattleDTO> characterAttack(@PathVariable(name = "id") Long characterId) {
        BattleDTO battleDTO = battleService.characterAttack(characterId);
        return ResponseEntity.ok(battleDTO);
    }

    @PutMapping(value = "/monster-attack/{characterId}")
    public ResponseEntity<BattleDTO> monsterAttack(@PathVariable(name = "characterId") Long id) {
        BattleDTO battleDTO = battleService.monsterAttack(id);
        return ResponseEntity.ok(battleDTO);
    }


}
