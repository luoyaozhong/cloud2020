package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyselfRule
 *
 * @author lxd
 * @date 2020/3/30
 **/
@Configuration
public class MyselfRule {

    @Bean
    public IRule myRule(){

        return new RandomRule();//定义为随机
    }

}
