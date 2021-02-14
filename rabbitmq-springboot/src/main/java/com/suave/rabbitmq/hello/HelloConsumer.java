package com.suave.rabbitmq.hello;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 默认的@Queue注解创建的队列 持久化 非独占 不自动删除
 *
 * @author Suave
 * @date 2021/2/14 6:49 下午
 */
@Component
@RabbitListener(queuesToDeclare = @Queue("hello"))
public class HelloConsumer {

    @RabbitHandler
    public void receive(String msg) {
        System.out.println("msg = " + msg);
    }
}
