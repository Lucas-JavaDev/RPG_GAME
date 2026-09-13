package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterHistoricDTO {

    private Integer totalBattles;
    private Long wins;
    private Long losses;
    private Double winRate;

}
