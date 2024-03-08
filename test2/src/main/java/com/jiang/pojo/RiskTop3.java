package com.jiang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jiangsj
 * @create 2024/3/8
 * @desc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiskTop3 {
    private String city;
    private Double riskIndex;
    private int rank;
}
