package com.jiang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiskTop {

    private String year;
    private String city;
    private Double riskIndex;
    private int rnk;

}
