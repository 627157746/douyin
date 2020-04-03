package com.zhb.douyin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/2 16:04
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private int code;
    private String msg;
    private Object data;

    static Result success(Object data) {
        return new Result(200, "success", data);
    }

    public static Result error() {
        return new Result(500, "error", null);
    }

}
