package com.suave.rabbitmq.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Suave
 * @date 2021/2/14 6:57 下午
 */
@Component
public class WorkConsumer {

    /**
     * 一个消费者
     *
     * @param msg1
     */
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receive1(String msg1) {
        System.out.println("msg1 = " + msg1);
    }

    /**
     * 一个消费者
     *
     * @param msg2
     */
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receive2(String msg2) {
        System.out.println("msg2 = " + msg2);
    }
}
