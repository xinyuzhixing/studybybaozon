package com.yzl.base.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * ClassName: KafkaConsumer
 * Description:
 * date: 2021/5/30 3:26 下午
 *
 * @author yangzhiliang
 */

@Component
public class BaseKafkaConsumer {

    // 消费监听
    @KafkaListener(topics = {"topic001"})
    public void getMessage1(ConsumerRecord<?, ?> record){
        System.out.println("消费："+record.topic()+"-"+record.partition()+"-"+record.value());
    }
}