package ru.arch.smarthouse.telemetry.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.arch.smarthouse.telemetry.model.TelemetryHistory
import ru.arch.smarthouse.telemetry.repository.model.TelemetryHistoryRecord
import java.util.UUID

@Repository
interface TelemetryHistoryRepository : JpaRepository<TelemetryHistoryRecord, Long> {
    fun save(telemetryHistory: TelemetryHistory): TelemetryHistory {
        return save(telemetryHistory.toRecord()).toTelemetryHistory()
    }

    fun findAllByDeviceId(deviceId: UUID): List<TelemetryHistory> {
        return listOf()
    }

    private fun TelemetryHistory.toRecord() = TelemetryHistoryRecord(
        deviceId = deviceId,
        value = value,
        unit = unit,
        status = status,
        createdAt = createdAt,
    )

    private fun TelemetryHistoryRecord.toTelemetryHistory() = TelemetryHistory(
        deviceId = deviceId,
        value = value,
        unit = unit,
        status = status,
        createdAt = createdAt,
    )
}