package ru.arch.smarthouse.device.repository.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "devices")
class DeviceRecord(
    @Id
    var id: UUID,

    @Column(nullable = false, unique = true)
    var externalId: String,

    @Column(nullable = false)
    var userId: UUID,

    @Column(nullable = true)
    var locationId: UUID?,

    @Column(nullable = true)
    var name: String?,

    @Column(nullable = true)
    var type: String,
)
