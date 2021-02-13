package com.suave;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Suave
 * @date 2021/2/13 2:55 下午
 */
public class ProviderTest {

    /**
     * 生产消息
     */
    @Test
    public void testSendMsg() throws IOException, TimeoutException {
        // 创建连接mq的连接工厂对象
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("/ems");
        factory.setUsername("ems");
        factory.setPassword("ems");
        // 获取连接对象
        Connection connection = factory.newConnection();
        // 获取连接通道
        Channel channel = connection.createChannel();
        // 通道绑定对应的消息队列
        // 参数一：队列名称 队列不存在则自动创建
        // 参数二：队列是否需要持久化
        // 参数三：是否独占队列 即是否只有当前的连接可用该队列
        // 参数四：是否在消费完成后自动删除队列
        // 参数五：额外附加参数
        channel.queueDeclare("hello", false, false, false, null);
        // 发布消息
        // 参数一：交换机名称
        // 参数二：队列名称
        // 参数三：传递消息额外设置
        // 参数四：消息的具体内容
        channel.basicPublish("", "hello", null, "hello rabbitmq".getBytes());
        channel.close();
        connection.close();
    }
}
