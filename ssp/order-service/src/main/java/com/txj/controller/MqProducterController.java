package com.txj.controller;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 生产者
 * @author: txj
 * @email: catchtom@qq.com
 * @date: 2021/4/3 17:24
 */
@RestController
@RequestMapping("/mqProducer")
public class MqProducterController {
    public static final Logger LOGGER = LoggerFactory.getLogger(MqProducterController.class);

    @Autowired
    DefaultMQProducer defaultMQProducer;

    /**
     * 发送简单的MQ消息
     * @param msg
     * @return
     */
    @GetMapping("/send")
    public Map<String,Object> send(String msg) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        Map<String,Object> result = new HashMap<>();
        if (StringUtils.isEmpty(msg)) {
            result.put("code",200);
            return result;
        }
        LOGGER.info("发送MQ消息内容：" + msg);
        Message testTopicTestTag = new Message("TestTopic", "TestTag", msg.getBytes());
        Message testTopicHelloTag = new Message("TestTopic", "HelloTag", msg.getBytes());
        Message helloTopicHelloTag = new Message("HelloTopic", "HelloTag", msg.getBytes());
        // 默认3秒超时
        SendResult result1 = defaultMQProducer.send(testTopicTestTag);
        LOGGER.info("消息1发送响应：" + result1.toString());

        SendResult result2 = defaultMQProducer.send(testTopicHelloTag);
        LOGGER.info("消息2发送响应：" + result2.toString());

        SendResult result3 = defaultMQProducer.send(helloTopicHelloTag);
        LOGGER.info("消息3发送响应：" + result3.toString());
        result.put("msg1","消息1发送响应：" + result1.toString());
        result.put("msg2","消息2发送响应：" + result2.toString());
        result.put("msg3","消息3发送响应：" + result3.toString());
        return result;
    }

}