package ru.arch.smarthouse.tempo_mock.adapter.kafka.model

import java.time.Instant

data class TelemetryEvent(
    val deviceExternalId: String,
    val value: String?,
    val unit: String?,
    val status: String,
    val createdAt: Instant,
)