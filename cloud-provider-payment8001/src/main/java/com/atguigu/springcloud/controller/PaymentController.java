package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * PaymentController
 *
 * @author lxd
 * @date 2020/3/17
 **/
@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value ="/create")
    public CommonResult create(@RequestBody Payment payment){
        int row = paymentService.create(payment);
        log.info("插入数据的ID:\t" + payment.getId());
        log.info("插入结果：" + row);


        if(row>0){
            return  new CommonResult(200, "插入数据成功,serverPort："+serverPort, row);
        }else{
            return  new CommonResult(444, "插入数据失败", row);
        }
    }

    @GetMapping(value ="/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("***查询结果：" + payment);

        if (payment != null){
            return new CommonResult(200, "查询数据成功,serverPort："+serverPort, payment);
        }else{
            return new CommonResult(444, "没有对应记录", null);
        }
    }

    @GetMapping(value ="/discovery")
    public Object discovery(){

        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("*****element: "+element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }

        return this.discoveryClient;
    }

    @GetMapping(value = "/lb")
    public String getPaymentLB()
    {
        return serverPort;
    }

    @GetMapping(value = "/feign/timeout")
    public String paymentFeignTimeout() {
        // 业务逻辑处理正确，但是需要耗费3秒钟
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        return serverPort;
    }

}
