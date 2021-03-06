package com.suave.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.suave.utils.RabbitmqUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Suave
 * @date 2021/2/14 4:11 下午
 */
public class ProviderTest {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitmqUtils.getConnection();
        Channel channel = connection.createChannel();
        // 通道声明指定交换机
        // 参数一：交换机名称
        // 参数二：交换机类型 fanout表示广播类型
        channel.exchangeDeclare("logs", "fanout");
        // 发送消息
        channel.basicPublish("logs", "", null, "fanout type message".getBytes());
        channel.close();
        connection.close();

    }
}
