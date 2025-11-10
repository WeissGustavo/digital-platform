package br.com.banking.digital_platform.config;

import com.google.gson.Gson;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    public static final String TRANSACTIONS_SENT_TOPIC = "transactions-sent";
    public static final String TRANSACTIONS_NOTIFICATIONS_TOPIC = "transactions-notifications";

    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.RETRIES_CONFIG, 3);
        configProps.put(ProducerConfig.ACKS_CONFIG, "all");
        configProps.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Bean
    public NewTopic transactionsSentTopic() {
        return new NewTopic(TRANSACTIONS_SENT_TOPIC, 1, (short) 1);
    }

    @Bean
    public NewTopic transactionsNotificationsTopic() {
        return new NewTopic(TRANSACTIONS_NOTIFICATIONS_TOPIC, 1, (short) 1);
    }
}