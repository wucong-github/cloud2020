package com.atguigu.springcloud.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 传给前端的 json 封装类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class CommonResult<T> {

    // 状态码 200 404
    private Integer code;
    // 报错信息
    private String message;
    // 实际数据，泛型通用
    private T date;

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }
}
