package ru.arch.smarthouse.device.adapter.kafka.model

import java.util.UUID

data class DeviceEvent(
    val id: UUID,
    val externalId: String,
    val userId: UUID,
    val locationId: UUID?,
    val name: String?,
    val eventType: EventType,
)

enum class EventType {
    CREATE, DELETE,
}