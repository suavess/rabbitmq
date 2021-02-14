package com.suave.fanout;

import com.rabbitmq.client.*;
import com.suave.utils.RabbitmqUtils;

import java.io.IOException;

/**
 * @author Suave
 * @date 2021/2/14 4:20 下午
 */
public class ConsumerTest3 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitmqUtils.getConnection();
        Channel channel = connection.createChannel();
        // 临时队列
        String queueName = channel.queueDeclare().getQueue();
        // 通道绑定临时队列
        channel.queueBind(queueName, "logs", "");
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
                System.out.println("消费者3：" + new String(body));
            }
        });
    }
}
