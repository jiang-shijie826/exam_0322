package com.jiang.mapper;

import com.jiang.pojo.RiskTop;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jiangsj
 * @create 2024/3/8
 * @desc
 */
@Mapper
@Repository
public interface CrimesRiskTopMapper {

    /**
     * CrimesRiskTop
     * @return list
     */
    List<RiskTop> queryCrimesRiskTop();

}
