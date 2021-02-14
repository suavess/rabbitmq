package com.suave.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.suave.utils.RabbitmqUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Suave
 * @date 2021/2/14 4:59 下午
 */
public class ProviderTest {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitmqUtils.getConnection();
        Channel channel = connection.createChannel();
        // 通道声明交换机
        // 参数一：交换机名称
        // 参数二：交换机类型 direct 路由模式
        channel.exchangeDeclare("logs_direct", "direct");
        String routeKey = "error";
        channel.basicPublish("logs_direct", routeKey, null, ("这是direct模型发出的基于Route Key:[" + routeKey + "]").getBytes());
        channel.close();
        connection.close();
    }
}