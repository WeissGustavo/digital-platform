package br.com.banking.digital_platform.kafka;

import br.com.banking.digital_platform.dto.TransactionEventDTO;
import br.com.banking.digital_platform.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionConsumer {

    @Autowired
    private TransactionService transactionService;

    @KafkaListener(topics = "transactions-sent", groupId = "transaction-group", containerFactory = "kafkaListenerContainerFactory")
    public void listenTransactionsSent(TransactionEventDTO event) {
        transactionService.processTransaction(event);
    }

    @KafkaListener(topics = "transactions-notifications", groupId = "transaction-group", containerFactory = "kafkaListenerContainerFactory")
    public void listenTransactionNotifications(TransactionEventDTO event) {
        transactionService.notifyReceivingUser(event);
    }
}
