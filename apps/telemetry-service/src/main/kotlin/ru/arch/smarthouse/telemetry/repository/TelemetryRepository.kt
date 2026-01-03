package ru.arch.smarthouse.telemetry.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.arch.smarthouse.telemetry.model.Telemetry
import ru.arch.smarthouse.telemetry.repository.model.TelemetryRecord
import java.util.UUID

@Repository
interface TelemetryRepository : JpaRepository<TelemetryRecord, UUID> {
    fun save(telemetry: Telemetry): Telemetry {
        return save(telemetry.toRecord()).toTelemetry()
    }

    fun findByDeviceId(deviceId: UUID): Telemetry? {
        return findById(deviceId).orElse(null)?.toTelemetry()
    }

    private fun Telemetry.toRecord() = TelemetryRecord(
        deviceId = deviceId,
        value = value,
        unit = unit,
        status = status,
        createdAt = createdAt,
    )

    private fun TelemetryRecord.toTelemetry() = Telemetry(
        deviceId = deviceId,
        value = value,
        unit = unit,
        status = status,
        createdAt = createdAt,
    )
}