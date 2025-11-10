package br.com.banking.digital_platform.kafka;

import br.com.banking.digital_platform.config.KafkaConfig;
import br.com.banking.digital_platform.dto.TransactionEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TransactionProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendTransaction(TransactionEventDTO event) {
        kafkaTemplate.send(KafkaConfig.TRANSACTIONS_SENT_TOPIC, event);
    }

    public void sendNotification(TransactionEventDTO event) {
        kafkaTemplate.send(KafkaConfig.TRANSACTIONS_NOTIFICATIONS_TOPIC, event);
    }
}
