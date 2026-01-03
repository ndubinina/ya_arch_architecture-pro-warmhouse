package ru.arch.smarthouse.telemetry.api.kafka

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import ru.arch.smarthouse.telemetry.api.kafka.model.DeviceEvent
import ru.arch.smarthouse.telemetry.api.kafka.model.EventType
import ru.arch.smarthouse.telemetry.model.Device
import ru.arch.smarthouse.telemetry.repository.DeviceRepository

@Service
class DeviceEventListener(
    private val deviceRepository: DeviceRepository,
) {
    @KafkaListener(
        topics = ["devices.events"],
        groupId = "telemetry-group",
        containerFactory = "kafkaDeviceListenerContainerFactory"
    )
    fun listen(event: DeviceEvent) {
        if (event.eventType == EventType.CREATE) {
            deviceRepository.save(
                Device(
                    deviceId = event.id,
                    externalId = event.externalId,
                    userId = event.userId,
                    locationId = event.locationId,
                )
            )
        }
    }
}