package ru.arch.smarthouse.telemetry.service

import org.springframework.stereotype.Service
import ru.arch.smarthouse.telemetry.model.Telemetry
import ru.arch.smarthouse.telemetry.model.TelemetryHistory
import ru.arch.smarthouse.telemetry.repository.TelemetryHistoryRepository
import ru.arch.smarthouse.telemetry.repository.TelemetryRepository
import java.util.UUID

@Service
class TelemetryService(
    private val telemetryRepository: TelemetryRepository,
    private val telemetryHistoryRepository: TelemetryHistoryRepository,
) {
    fun getByDeviceId(deviceId: UUID): Telemetry? {
        return telemetryRepository.findByDeviceId(deviceId)
    }

    fun save(telemetry: Telemetry): Telemetry {
        val existTelemetry = telemetryRepository.findByDeviceId(telemetry.deviceId)
        when {
            existTelemetry == null -> telemetryRepository.save(telemetry)
            existTelemetry.createdAt <= telemetry.createdAt -> {
                telemetryHistoryRepository.save(TelemetryHistory.from(telemetry))
                telemetryRepository.save(telemetry)
            }
            else -> println("Telemetry in DB is more fresh. Don't need to update.")
        }
        return telemetryRepository.save(telemetry)
    }

    fun getByLocationId(locationId: UUID): List<Telemetry> {
        return listOf()
    }
}