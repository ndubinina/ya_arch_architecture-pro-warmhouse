package ru.arch.smarthouse.telemetry.repository.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "telemetry_history")
class TelemetryHistoryRecord(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false)
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