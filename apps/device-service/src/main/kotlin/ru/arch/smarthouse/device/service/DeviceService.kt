package ru.arch.smarthouse.device.service

import org.springframework.stereotype.Service
import ru.arch.smarthouse.device.adapter.device_external.AdapterDeviceExternalSystem
import ru.arch.smarthouse.device.adapter.kafka.KafkaProducer
import ru.arch.smarthouse.device.model.Device
import ru.arch.smarthouse.device.model.exception.NotFoundException
import ru.arch.smarthouse.device.repository.DeviceRepository
import java.util.UUID

@Service
class DeviceService(
    private val deviceRepository: DeviceRepository,
    private val adapterDeviceExternalSystem: AdapterDeviceExternalSystem,
    private val kafkaProducer: KafkaProducer,
) {
    fun register(device: Device): Device {
        val device = deviceRepository.save(device)
        kafkaProducer.publishOnCreate(device)
        return device
    }

    fun get(deviceId: UUID): Device?  =
        deviceRepository.findByDeviceId(deviceId)

    fun delete(deviceId: UUID, userId: UUID) {
        val device = deviceRepository.findByDeviceId(deviceId)
        if (device == null || device.userId != userId) {
            throw NotFoundException()
        }
        deviceRepository.deleteById(deviceId)
        kafkaProducer.publishOnDelete(device)
    }


    fun control(deviceId: UUID, userId: UUID, value: String, status: String) {
        val device = deviceRepository.findByDeviceId(deviceId)
        if (device == null || device.userId != userId) {
            throw NotFoundException()
        }
        adapterDeviceExternalSystem.control(device = device, value = value, status = status)
    }
}