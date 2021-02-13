package com.suave;

import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

/**
 * @author Suave
 * @date 2021/2/13 2:55 下午
 */
public class ProviderTest {

    /**
     * 生产消息
     */
    @Test
    public void testSendMsg() {
        // 创建连接mq的连接工厂对象
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("/ems");
        factory.setUsername("ems");
        factory.setPassword("ems");

    }
}
