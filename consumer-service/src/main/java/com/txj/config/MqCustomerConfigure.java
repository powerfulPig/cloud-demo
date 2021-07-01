package com.txj.config;

import lombok.Getter;
import lombok.Setter;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 生产者配置
 * @author: txj
 * @email: catchtom@qq.com
 * @date: 2021/4/3 16:12
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "rocketmq.customer")
public class MqCustomerConfigure {

    private String groupName;
    private String namesrvAddr;
    private String topics;
    //消费者线程数量
    private Integer consumeThreadMin;
    private Integer consumeThreadMax;
    private Integer consumeMessageBatchMaxSize;
    @Autowired
    private MqConsumeMsgListenerProcessor msgListenerProcessor;
    @Bean
    @ConditionalOnProperty(prefix = "rocketmq.customer", value = "autoconfig", havingValue = "true")
    public DefaultMQPushConsumer defaultMQConsumer() throws MQClientException {
        System.out.println("正在创建消费者。。。");
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);

        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.setConsumeMessageBatchMaxSize(consumeMessageBatchMaxSize);
        //设置启动从队列头、尾开始
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        //设置消费模型，默认为集群
//        consumer.setMessageModel(MessageModel.CLUSTERING);
        //设置消费者订阅的主题和tag，如果消费者订阅该主题下的所有tag则用*
        try{
            String[] params = topics.split(";");
            for (String s:params){
                String[] topicAndTag = s.split("~");
                consumer.subscribe(topicAndTag[0],topicAndTag[1]);
            }
            //设置监听
            consumer.registerMessageListener(msgListenerProcessor);
            consumer.start();
            System.out.println("消费者启动。。。");
        }catch (MQClientException e){
            e.printStackTrace();
            System.out.println("消费者创建失败");
        }

        return consumer;
    }

}
