package ru.arch.smarthouse.telemetry.repository.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "telemetry")
class TelemetryRecord(
    @Id
    var deviceId: UUID,

    @Column(nullable = true)
    var value: String?,

    @Column(nullable = true)
    var unit: String?,

    @Column(nullable = false)
    var status: String,

    @Column(nullable = false)
    var createdAt: Instant,
)