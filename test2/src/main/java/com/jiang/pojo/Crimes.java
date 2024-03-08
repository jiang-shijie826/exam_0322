package com.jiang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author jiangsj
 * @create 2024/3/8
 * @desc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crimes {

    private String year;

    private List<RiskTop3> riskTop3;

}
