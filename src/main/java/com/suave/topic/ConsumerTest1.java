package com.suave.topic;

import com.rabbitmq.client.*;
import com.suave.utils.RabbitmqUtils;

import java.io.IOException;

/**
 * @author Suave
 * @date 2021/2/14 5:39 下午
 */
public class ConsumerTest1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitmqUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("topics", "topic");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, "topics", "user.*");
        channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
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
                System.out.println("消费者1：" + new String(body));
            }
        });
    }
}
