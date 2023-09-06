package com.ryasnov.transactionservice.controllers;
import com.ryasnov.transactionservice.entities.Transaction;
import com.ryasnov.transactionservice.producer.RabbitMQJsonProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageJsonController {

    private final RabbitMQJsonProducer producer;

    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody Transaction transaction) {

        producer.sendJsonMessage(transaction);

        return new ResponseEntity<>("Json message " + transaction + " has sent to RabbitMQ", HttpStatus.OK);

    }

}
