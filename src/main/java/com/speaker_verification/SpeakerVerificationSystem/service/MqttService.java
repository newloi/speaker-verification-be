package com.speaker_verification.SpeakerVerificationSystem.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MqttService {
    MqttClient mqttClient;

    @Async
    public void publishAsync(String topic, String message) {
        try {
            mqttClient.publish(topic, message.getBytes(), 0, true);
        } catch (MqttException e) {
            log.error("Publish message error: {}", e.getMessage());
        }
    }
}
