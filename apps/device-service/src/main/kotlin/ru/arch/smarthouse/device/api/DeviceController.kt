package ru.arch.smarthouse.device.api

import io.swagger.v3.oas.annotations.*
import io.swagger.v3.oas.annotations.enums.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.*
import org.springframework.validation.annotation.Validated

import jakarta.validation.Valid
import ru.arch.smarthouse.device.api.model.CreateDeviceRequest
import ru.arch.smarthouse.device.api.model.CreateDeviceResponse
import ru.arch.smarthouse.device.api.model.DeviceControlRequest
import ru.arch.smarthouse.device.api.model.DeviceResponse
import ru.arch.smarthouse.device.model.Device
import ru.arch.smarthouse.device.model.DeviceType
import ru.arch.smarthouse.device.model.exception.NotFoundException
import ru.arch.smarthouse.device.service.DeviceService
import java.util.UUID

@RestController
@Validated
@RequestMapping("\${api.base-path:}")
class DeviceController(private val deviceService: DeviceService) {

    @RequestMapping(
        method = [RequestMethod.PATCH],
        value = ["/v1/device/{deviceId}/control"],
        consumes = ["application/json"]
    )
    fun v1DeviceDeviceIdControlPatch(@Parameter(description = "id пользователя", `in` = ParameterIn.HEADER, required = true) @RequestHeader(value = "userId", required = true) userId: kotlin.String,@Parameter(description = "Идентификатор датчика", required = true) @PathVariable("deviceId") deviceId: kotlin.String,@Parameter(description = "", required = true) @Valid @RequestBody deviceControlRequest: DeviceControlRequest): ResponseEntity<Unit> {
        deviceService.control(
            deviceId = UUID.fromString(deviceId),
            userId = UUID.fromString(userId),
            value = deviceControlRequest.value,
            status = deviceControlRequest.status,
        )
        return ResponseEntity(HttpStatus.ACCEPTED)
    }

    @RequestMapping(
        method = [RequestMethod.DELETE],
        value = ["/v1/device/{deviceId}"]
    )
    fun v1DeviceDeviceIdDelete(@Parameter(description = "id пользователя", `in` = ParameterIn.HEADER, required = true) @RequestHeader(value = "userId", required = true) userId: kotlin.String,@Parameter(description = "Идентификатор датчика", required = true) @PathVariable("deviceId") deviceId: kotlin.String): ResponseEntity<Unit> {
        deviceService.delete(deviceId = UUID.fromString(deviceId), userId = UUID.fromString(userId))
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/v1/device"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun v1DevicePost(@Parameter(description = "id пользователя", `in` = ParameterIn.HEADER, required = true) @RequestHeader(value = "userId", required = true) userId: kotlin.String,@Parameter(description = "", required = true) @Valid @RequestBody createDeviceRequest: CreateDeviceRequest): ResponseEntity<CreateDeviceResponse> {
        val device = deviceService.register(createDeviceRequest.toDevice(userId))
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateDeviceResponse(device.id.toString()))
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v1/device/{deviceId}"],
        produces = ["application/json"]
    )
    fun v1DeviceDeviceIdGet(@Parameter(description = "id пользователя", `in` = ParameterIn.HEADER, required = true) @RequestHeader(value = "userId", required = true) userId: kotlin.String,@Parameter(description = "Идентификатор датчика", required = true) @PathVariable("deviceId") deviceId: kotlin.String): ResponseEntity<DeviceResponse> {
        val device = deviceService.get(UUID.fromString(deviceId))
        if (device == null || userId != device.userId.toString()) {
            throw NotFoundException()
        }

        return ResponseEntity.ok(device.toDeviceResponse())
    }

    private fun CreateDeviceRequest.toDevice(userId: String) = Device(
        id = UUID.randomUUID(),
        externalId = externalId,
        userId = UUID.fromString(userId),
        locationId = locationId?.let { UUID.fromString(it) },
        name = name,
        type = DeviceType.from(type),
    )

    private fun Device.toDeviceResponse() = DeviceResponse(
        deviceId = id.toString(),
        externalId = externalId,
        locationId = locationId.toString(),
        name = name,
        type = type.name,
    )
}
