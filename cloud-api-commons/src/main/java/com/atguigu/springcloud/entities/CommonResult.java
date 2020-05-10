package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CommonResult
 *
 * @author lxd
 * @date 2020/3/17
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code;
    private String message;
    private T       data;

    public CommonResult success(Integer code,String message){
        return new CommonResult(code,message,null);
    }


}
