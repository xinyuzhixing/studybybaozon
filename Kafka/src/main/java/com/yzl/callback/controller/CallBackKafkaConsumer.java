package com.yzl.callback.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class CallBackKafkaConsumer {

    // 消费监听
    @KafkaListener(topics = {"topic002"})
    public void getMessage2(ConsumerRecord<?, ?> record){
        System.out.println("消费："+record.topic()+"-"+record.partition()+"-"+record.value());
    }

    // 消费监听
    @KafkaListener(topics = {"topic003"})
    public void getMessage3(ConsumerRecord<?, ?> record){
        System.out.println("消费："+record.topic()+"-"+record.partition()+"-"+record.value());
    }
}