package ru.arch.smarthouse.telemetry.model

import java.time.Instant
import java.util.UUID

data class TelemetryHistory(
    val deviceId: UUID,
    val value: String?,
    val unit: String?,
    val status: String,
    val createdAt: Instant,
) {
    companion object {
        fun from(telemetry: Telemetry): TelemetryHistory {
            return TelemetryHistory(
                deviceId = telemetry.deviceId,
                value = telemetry.value,
                unit = telemetry.unit,
                status = telemetry.status,
                createdAt = telemetry.createdAt,
            )
        }
    }
}