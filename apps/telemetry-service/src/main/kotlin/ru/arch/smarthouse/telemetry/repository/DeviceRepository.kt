package ru.arch.smarthouse.telemetry.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.arch.smarthouse.telemetry.model.Device
import ru.arch.smarthouse.telemetry.repository.model.DeviceRecord
import java.util.Optional
import java.util.UUID

@Repository
interface DeviceRepository : JpaRepository<DeviceRecord, UUID> {
    fun save(device: Device): Device {
        return save(device.toRecord()).toDevice()
    }

    fun findByDeviceId(deviceId: UUID): Device? {
        return findById(deviceId).orElse(null)?.toDevice()
    }

    fun findByExternalId(externalId: String): Optional<DeviceRecord>

    fun findExternalId(externalId: String): Device? {
        return findByExternalId(externalId).orElse(null)?.toDevice()
    }

    private fun Device.toRecord() = DeviceRecord(
        deviceId = deviceId,
        externalId = externalId,
        userId = userId,
        locationId = locationId,
    )

    private fun DeviceRecord.toDevice() = Device(
        deviceId = deviceId,
        externalId = externalId,
        userId = userId,
        locationId = locationId,
    )
}