package com.suave.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqSpringbootApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * hello world
     */
    @Test
    void testHelloWorld() {
        rabbitTemplate.convertAndSend("hello", "hello world");
    }

    /**
     * work模型
     */
    @Test
    public void testWork() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work", "work模型" + i);
        }
    }

    /**
     * work模型
     */
    @Test
    public void testFanout() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("logs", "", "work模型" + i);
        }
    }

}
