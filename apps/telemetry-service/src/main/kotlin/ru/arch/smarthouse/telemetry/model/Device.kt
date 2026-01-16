package ru.arch.smarthouse.telemetry.model

import java.util.UUID

data class Device(
    val deviceId: UUID,
    val externalId: String,
    val userId: UUID,
    val locationId: UUID?,
)