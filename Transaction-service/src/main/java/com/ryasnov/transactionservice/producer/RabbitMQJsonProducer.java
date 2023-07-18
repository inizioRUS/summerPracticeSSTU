package com.ryasnov.transactionservice.producer;

import com.kishko.userservice.entities.User;
import com.ryasnov.transactionservice.entities.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendJsonMessage(Transaction transaction) {

        LOGGER.info(String.format("Json message sent -> %s", transaction));
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, transaction);

    }

}