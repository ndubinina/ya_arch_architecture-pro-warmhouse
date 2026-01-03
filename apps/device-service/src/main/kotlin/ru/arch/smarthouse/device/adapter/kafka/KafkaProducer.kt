package ru.arch.smarthouse.device.adapter.kafka

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import ru.arch.smarthouse.device.adapter.kafka.model.DeviceEvent
import ru.arch.smarthouse.device.adapter.kafka.model.EventType
import ru.arch.smarthouse.device.model.Device

@Service
class KafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, DeviceEvent>
) {
    fun publishOnCreate(device: Device) {
        kafkaTemplate.send(
            "devices.events",
            device.id.toString(),
            device.toEvent(EventType.CREATE),
        )
    }

    fun publishOnDelete(device: Device) {
        kafkaTemplate.send(
            "devices.events",
            device.id.toString(),
            device.toEvent(EventType.DELETE),
        )
    }

    private fun Device.toEvent(eventType: EventType) = DeviceEvent(
        id = id,
        externalId = externalId,
        userId = userId,
        locationId = locationId,
        name = name,
        eventType = eventType,
    )
}