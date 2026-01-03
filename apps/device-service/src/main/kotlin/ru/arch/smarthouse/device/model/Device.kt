package ru.arch.smarthouse.device.model

import java.util.UUID

data class Device(
    val id: UUID,
    val externalId: String,
    val userId: UUID,
    val type: DeviceType,
    val locationId: UUID?,
    val name: String?,
)