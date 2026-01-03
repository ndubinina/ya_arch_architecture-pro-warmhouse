package ru.arch.smarthouse.telemetry.api.kafka.model

import java.time.Instant

data class TelemetryEvent(
    val deviceExternalId: String,
    val value: String?,
    val unit: String?,
    val status: String,
    val createdAt: Instant,
)