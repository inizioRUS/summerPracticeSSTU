package com.kishko.userservice.controllers;

import com.kishko.userservice.entities.User;
import com.kishko.userservice.producer.RabbitMQJsonProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageJsonController {

    @Autowired
    private RabbitMQJsonProducer producer;

    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user) {

        producer.sendJsonMessage(user);

        return new ResponseEntity<>("Json message " + user + " has sent to RabbitMQ", HttpStatus.OK);

    }

}
