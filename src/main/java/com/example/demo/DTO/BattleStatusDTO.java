package com.example.demo.DTO;


import com.example.demo.Entity.Battle;
import com.example.demo.Entity.Enum.BattleStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BattleStatusDTO {

    private Long battleId;
    private BattleStatus status;

    public BattleStatusDTO(Battle battle) {
        battleId = battle.getId();
        status = battle.getStatus();
    }
}
