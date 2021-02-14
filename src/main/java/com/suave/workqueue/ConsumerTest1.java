package com.suave.workqueue;

import com.rabbitmq.client.*;
import com.suave.utils.RabbitmqUtils;

import java.io.IOException;

/**
 * @author Suave
 * @date 2021/2/14 1:56 下午
 */
public class ConsumerTest1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitmqUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1);
        channel.queueDeclare("work", true, false, false, null);
        // 参数一：队列名称 参数二：消息自动确认，即消费者自动告诉消息队列消息被消费
        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            /**
             * No-op implementation of {@link Consumer#handleDelivery}.
             *
             * @param consumerTag
             * @param envelope
             * @param properties
             * @param body
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-1：" + new String(body));
                // 通道确认消息
                // 参数一：确认是队列中的哪个具体消息
                // 参数二：是否开启多个消息同时确认
                channel.basicAck(envelope.getDeliveryTag(), false);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
