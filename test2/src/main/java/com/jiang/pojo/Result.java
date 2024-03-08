package com.jiang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author jiangsj
 * @create 2024/3/8
 * @desc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private static final int SUCCESS_CODE = 0;
    private static final int ERROR_CODE = -1;

    private int code;
    private String message;
    private Object result;

    /**
     * 有参数的成功方法，返回成功代码“200” 和 数据data
     * @param data
     * @return
     */
    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        result.setMessage("ok");
        result.setResult(data);
        return result;
    }

    public static Result error(int code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }

}
