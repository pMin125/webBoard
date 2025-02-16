package com.onion.backend.service;

import com.onion.backend.pojo.WriteArticle;
import com.onion.backend.pojo.SendCommentNotification;
import com.onion.backend.pojo.WriteComment;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class RabbitMQSender {
    private final RabbitTemplate rabbitTemplate;

    public void send(WriteArticle articleNotification) {
        rabbitTemplate.convertAndSend("onion-notification", articleNotification.toString());
    }

    public void send(WriteComment message) {
        rabbitTemplate.convertAndSend("onion-notification", message.toString());
    }

    public void send(SendCommentNotification message) {
        rabbitTemplate.convertAndSend("send_notification_exchange", "", message.toString());
    }
}
