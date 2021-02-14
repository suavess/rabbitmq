package com.suave.rabbitmq.route;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Suave
 * @date 2021/2/14 7:25 下午
 */
@Component
public class RouteConsumer {

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue,
                    key = {"info", "error", "warning"},
                    exchange = @Exchange(
                            value = "directs", type = "direct"
                    )
            )
    )
    public void receive1(String message1) {
        System.out.println("message1 = " + message1);
    }

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue,
                    key = {"error"},
                    exchange = @Exchange(
                            value = "directs", type = "direct"
                    )
            )
    )
    public void receive2(String message2) {
        System.out.println("message2 = " + message2);
    }
}
