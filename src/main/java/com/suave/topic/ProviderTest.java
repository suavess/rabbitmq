package com.suave.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.suave.utils.RabbitmqUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Suave
 * @date 2021/2/14 5:36 下午
 */
public class ProviderTest {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitmqUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("topics", "topic");
        String routeKey = "user.save";
        channel.basicPublish("topics", routeKey, null, ("这是topic动态路由模型，routeKey:[" + routeKey + "]").getBytes());
        channel.close();
        connection.close();
    }
}
