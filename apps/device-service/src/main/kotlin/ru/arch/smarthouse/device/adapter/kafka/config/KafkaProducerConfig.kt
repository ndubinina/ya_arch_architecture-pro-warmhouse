package ru.arch.smarthouse.device.adapter.kafka.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer
import ru.arch.smarthouse.device.adapter.kafka.model.DeviceEvent
import kotlin.Any
import kotlin.String

@Configuration
class KafkaProducerConfig() {
    @Value("\${kafka.bootstrap.servers}")
    lateinit var kafkaBootstrapServers: String

    @Bean
    fun producerFactory(): ProducerFactory<String, DeviceEvent> {
        val props = mutableMapOf<String, Any>(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaBootstrapServers,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java.getName(),
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java.getName(),
            ProducerConfig.ACKS_CONFIG to "all",
            ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG to "true",
            "spring.json.add.type.headers" to false,
        )

        return DefaultKafkaProducerFactory(props)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, DeviceEvent> {
        return KafkaTemplate(producerFactory())
    }
}