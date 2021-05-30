package com.yzl.callback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: KafkaProducer
 * Description:
 * date: 2021/5/30 3:35 下午
 *
 * @author yangzhiliang
 */

@RestController
public class CallBackKafkaProducer {

    // 自动注入kafkaTemplate
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;


    /**
     * @description: 生产者生产消息
     * @author: yangzhiliang
     * @date: 2021/5/30
     * @param callbackMessage: 返回消息
     * @return:
     *          发送消息成功返回：
     *          发送消息失败返回：
     */

    @GetMapping("/kafka/callback/{message}")
    public void sendMessage(@PathVariable("message") String callbackMessage) {
        kafkaTemplate.send("topic002", callbackMessage).addCallback(success -> {
            String topic = success.getRecordMetadata().topic();
            int partition = success.getRecordMetadata().partition();
            long offset = success.getRecordMetadata().offset();
            System.out.println("发送消息成功:" + topic + "-" + partition + "-" + offset);
        }, failure -> {
            System.out.println("发送消息失败:" + failure.getMessage());
        });
    }


    @GetMapping("/kafka/callback2/{message}")
    public void sendMessage3(@PathVariable("message") String callbackMessage) {
        kafkaTemplate.send("topic003", callbackMessage).addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("发送消息失败："+ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                System.out.println("发送消息成功：" + result.getRecordMetadata().topic() + "-"
                        + result.getRecordMetadata().partition() + "-" + result.getRecordMetadata().offset());
            }
        });
    }

}