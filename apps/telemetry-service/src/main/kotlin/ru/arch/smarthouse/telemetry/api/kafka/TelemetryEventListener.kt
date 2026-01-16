package ru.arch.smarthouse.telemetry.api.kafka

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import ru.arch.smarthouse.telemetry.api.kafka.model.TelemetryEvent
import ru.arch.smarthouse.telemetry.model.Device
import ru.arch.smarthouse.telemetry.model.Telemetry
import ru.arch.smarthouse.telemetry.repository.DeviceRepository
import ru.arch.smarthouse.telemetry.service.TelemetryService
import java.util.UUID

@Service
class TelemetryEventListener(
    private val telemetryService: TelemetryService,
    private val deviceRepository: DeviceRepository,
) {
    @KafkaListener(
        topics = ["telemetry.events"],
        groupId = "telemetry-group",
        containerFactory = "kafkaTelemetryListenerContainerFactory"
    )
    fun listen(event: TelemetryEvent) {
        val device = deviceRepository.findExternalId(event.deviceExternalId) ?: return
        val telemetry = Telemetry(
            deviceId = device.deviceId,
            value = event.value,
            unit = event.unit,
            status = event.status,
            createdAt = event.createdAt,
        )
        telemetryService.save(telemetry)
    }
}