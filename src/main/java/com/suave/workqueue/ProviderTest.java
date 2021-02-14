package com.suave.workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.suave.utils.RabbitmqUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Suave
 * @date 2021/2/13 9:20 下午
 */
public class ProviderTest {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitmqUtils.getConnection();
        Channel channel = connection.createChannel();
        // 声明消息队列
        channel.queueDeclare("work", true, false, false, null);
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("", "work", null, ("hello work queue" + i).getBytes());
        }
        channel.close();
        connection.close();
    }
}
