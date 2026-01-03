package ru.arch.smarthouse.telemetry.repository.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "device")
class DeviceRecord(
    @Id
    var deviceId: UUID,

    @Column(nullable = true)
    var externalId: String,

    @Column(nullable = false)
    var userId: UUID,

    @Column(nullable = true)
    var locationId: UUID?,
)