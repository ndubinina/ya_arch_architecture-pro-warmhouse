package ru.arch.smarthouse.telemetry.model

import java.time.Instant
import java.util.UUID

data class Telemetry(
    val deviceId: UUID,
    val value: String?,
    val unit: String?,
    val status: String,
    val createdAt: Instant,
)
