package ru.arch.smarthouse.telemetry.api.kafka.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer
import ru.arch.smarthouse.telemetry.api.kafka.model.DeviceEvent
import ru.arch.smarthouse.telemetry.api.kafka.model.TelemetryEvent
import kotlin.jvm.java

@EnableKafka
@Configuration
class KafkaConsumerConfig {
    @Value("\${kafka.bootstrap.servers}")
    lateinit var kafkaBootstrapServers: String

    @Value("\${kafka.consumer_group}")
    lateinit var kafkaConsumerGroup: String

    @Bean
    fun consumerTelemetryFactory(): ConsumerFactory<String, TelemetryEvent> {
        val deserializer = JsonDeserializer(TelemetryEvent::class.java)
        deserializer.addTrustedPackages("*")

        val props = mapOf<String, Any>(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaBootstrapServers,
            ConsumerConfig.GROUP_ID_CONFIG to kafkaConsumerGroup,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to deserializer
        )
        return DefaultKafkaConsumerFactory(props, StringDeserializer(), deserializer)
    }

    @Bean
    fun kafkaTelemetryListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, TelemetryEvent> {
        return ConcurrentKafkaListenerContainerFactory<String, TelemetryEvent>().apply {
            setConsumerFactory(consumerTelemetryFactory())
        }
    }

    @Bean
    fun consumerDeviceFactory(): ConsumerFactory<String, DeviceEvent> {
        val deserializer = JsonDeserializer(DeviceEvent::class.java)
        deserializer.addTrustedPackages("*")

        val props = mapOf<String, Any>(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaBootstrapServers,
            ConsumerConfig.GROUP_ID_CONFIG to kafkaConsumerGroup,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to deserializer
        )
        return DefaultKafkaConsumerFactory(props, StringDeserializer(), deserializer)
    }

    @Bean
    fun kafkaDeviceListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, DeviceEvent> {
        return ConcurrentKafkaListenerContainerFactory<String, DeviceEvent>().apply {
            setConsumerFactory(consumerDeviceFactory())
        }
    }
}