package com.txj.config;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @description: 消息监听器
 * @author: txj
 * @email: catchtom@qq.com
 * @date: 2021/4/3 17:06
 */
@Component
public class MqConsumeMsgListenerProcessor implements MessageListenerConcurrently {
    public static final Logger LOGGER = LoggerFactory.getLogger(MqConsumeMsgListenerProcessor.class);
    /**
     *
     * @param list 消息，可通过consumeMessageBatchMaxSize设置批量接收，默认一条
     * @param consumeConcurrentlyContext
     * @return CONSUME_SUCCESS代表消费成功
     */
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        if(CollectionUtils.isEmpty(list)){
            LOGGER.info("获取消息为空，直接返回成功");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        try{
            for(MessageExt msg:list){
                LOGGER.info("MQ接收到的消息为："+msg.toString());
                String topic = msg.getTopic();
                String tags = msg.getTags();
                String content = new String(msg.getBody(),"utf-8");
                LOGGER.info("消息主题为：{}；标签为：{}；内容为：{}",topic,tags,content);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
