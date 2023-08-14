package com.wisedevlife.whytalkmessage.common.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MqUtil {
  private final RabbitTemplate rabbitTemplate;

  public void sendMessage(String queue, String message) {
    rabbitTemplate.convertAndSend(queue, message);
  }
}
