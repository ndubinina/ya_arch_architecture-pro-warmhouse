package ru.arch.smarthouse.tempo_mock.adapter.kafka

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import ru.arch.smarthouse.tempo_mock.adapter.kafka.model.TelemetryEvent
import java.time.Instant
import kotlin.String

@Service
class KafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, TelemetryEvent>
) {
    fun publish(deviceId: String, value: Float) {
        val event = TelemetryEvent(
            deviceExternalId = deviceId,
            value = value.toString(),
            status = "active",
            createdAt = Instant.now(),
            unit = "F",
        )
        kafkaTemplate.send(
            "telemetry.events",
            deviceId,
            event,
        )
    }
}