package com.atguigu.springcloud.service;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

/**
 * PaymentFallBackService
 *
 * @author lxd
 * @date 2020/4/19
 **/
@Component
public class PaymentFallBackService implements  PaymentHystrixService {


    @Override
    public String paymentInfo_OK(Integer id) {
        return "-----PaymentFallBackService-----Fall Back-----paymentInfo_OK o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "-----PaymentFallBackService-----Fall Back-----paymentInfo_TimeOut o(╥﹏╥)o";
    }
}
