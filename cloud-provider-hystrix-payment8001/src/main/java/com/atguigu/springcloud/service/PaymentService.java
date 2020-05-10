package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * PaymentService
 *
 * @author lxd
 * @date 2020/4/19
 **/
@Service
public class PaymentService {

    /**
     * 正常访问
     *
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id) {
        return "线程池:" + Thread.currentThread().getName() + " paymentInfo_OK,id:" + id + "\t" + "O(∩_∩)O哈哈~";
    }

    /**
     * 超时访问
     *
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "payment_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")})
    public String paymentInfo_TimeOut(Integer id) {
        int timeNumber = 5000;
        try {
            //int age = 10 /0;
            // 暂停3秒钟
            TimeUnit.MILLISECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:" + Thread.currentThread().getName() + " id:" + id + "\t" + "O(∩_∩)O哈哈~  耗时(秒)" + timeNumber;
    }

    public String payment_TimeOutHandler(Integer id) {
        return "线程池:" + Thread.currentThread().getName() + " 8001系统繁忙或者运行报错，请稍后再试！" + id + "\t" + "o(╥﹏╥)o ";
    }

}
