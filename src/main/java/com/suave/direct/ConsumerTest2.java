package com.suave.direct;

import com.rabbitmq.client.*;
import com.suave.utils.RabbitmqUtils;

import java.io.IOException;

/**
 * @author Suave
 * @date 2021/2/14 5:05 下午
 */
public class ConsumerTest2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitmqUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("logs_direct", "direct");
        // 创建一个临时队列
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, "logs_direct", "info");
        channel.queueBind(queueName, "logs_direct", "error");
        channel.queueBind(queueName, "logs_direct", "warning");
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
                System.out.println("消费者2：" + new String(body));
            }
        });
    }
}
