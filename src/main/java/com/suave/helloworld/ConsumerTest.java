package com.suave.helloworld;

import com.rabbitmq.client.*;
import com.suave.utils.RabbitmqUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Suave
 * @date 2021/2/13 3:16 下午
 */
public class ConsumerTest {

    /**
     * JunitTest执行会直接关闭进程，取不到回调后的参数
     *
     * @throws IOException
     * @throws TimeoutException
     */
    @Test
    public void consume() throws IOException, TimeoutException {
        Connection connection = RabbitmqUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("hello", false, false, false, null);
        // 消费消息
        // 参数一：消费消息的队列
        // 参数二：开启消息的自动确认机制
        // 参数三：消费时的回调接口
        channel.basicConsume("hello", true, new DefaultConsumer(channel) {
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
                System.out.println("body = " + new String(body));
            }
        });
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitmqUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("hello", false, false, false, null);
        // 消费消息
        // 参数一：消费消息的队列
        // 参数二：开启消息的自动确认机制
        // 参数三：消费时的回调接口
        channel.basicConsume("hello", true, new DefaultConsumer(channel) {
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
                System.out.println("body = " + new String(body));
            }
        });
        // 一直监听，最好不关闭
//        channel.close();
//        connection.close();
    }
}
