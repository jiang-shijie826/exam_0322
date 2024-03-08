package com.jiang.controller;

import com.jiang.mapper.CrimesRiskTopMapper;
import com.jiang.pojo.Crimes;
import com.jiang.pojo.Result;
import com.jiang.pojo.RiskTop;
import com.jiang.pojo.RiskTop3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/crime")
public class CrimesRiskTopController {
    @Autowired
    private CrimesRiskTopMapper crimesRiskTopMapper;

    //查询所有
    @GetMapping("/stat")
    public Result queryCrimesRiskTop(){
        Result result = new Result();
        List<RiskTop> crimesList = crimesRiskTopMapper.queryCrimesRiskTop();
        if (!crimesList.isEmpty()){
            Map<String, List<RiskTop>> crimesRiskTopMap = crimesList.stream().collect(Collectors.groupingBy(RiskTop::getYear));
            List<Crimes> crimes = new ArrayList<>();
            for (Map.Entry<String, List<RiskTop>> entry : crimesRiskTopMap.entrySet()) {
                List<RiskTop> value = entry.getValue();
                List<RiskTop3> riskTop3List = new ArrayList<>();
                value.forEach(s -> {
                    riskTop3List.add(new RiskTop3(s.getCity(), s.getRiskIndex(), s.getRnk()));
                });
                crimes.add(new Crimes(entry.getKey(), riskTop3List));
            }
            result = Result.success(crimes);
        }else {
            Result.error(-1, "暂无城市数据");
        }
        return result;
    }

    @PostMapping
    public Result saveCrimesRiskTop(){
        return Result.success(1);
    }

}
