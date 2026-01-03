package ru.arch.smarthouse.device.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.arch.smarthouse.device.model.Device
import ru.arch.smarthouse.device.model.DeviceType
import ru.arch.smarthouse.device.repository.model.DeviceRecord
import java.util.UUID

@Repository
interface DeviceRepository : JpaRepository<DeviceRecord, UUID> {
    fun save(device: Device): Device {
        return save(device.toRecord()).toDevice()
    }

    fun findByDeviceId(deviceId: UUID): Device? =
        findById(deviceId).orElse(null)?.toDevice()

    private fun Device.toRecord() = DeviceRecord(
        id = id,
        externalId = externalId,
        userId = userId,
        locationId = locationId,
        name = name,
        type = type.name,
    )

    private fun DeviceRecord.toDevice() = Device(
        id = id,
        externalId = externalId,
        userId = userId,
        locationId = locationId,
        name = name,
        type = DeviceType.from(type),
    )
}