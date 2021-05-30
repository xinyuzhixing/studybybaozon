package com.yzl.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: KafkaProducer
 * Description:
 * date: 2021/5/30 3:21 下午
 *
 * @author yangzhiliang
 */


@RestController
public class BaseKafkaProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    // 发送消息
    @GetMapping("/kafka/basic/{message}")
    public void sendMessage(@PathVariable("message") String message) {
        kafkaTemplate.send("topic001", message);
    }




}