package ru.arch.smarthouse.telemetry.api

import ru.arch.smarthouse.telemetry.api.model.DeviceTelemetry
import io.swagger.v3.oas.annotations.*
import io.swagger.v3.oas.annotations.enums.*
import io.swagger.v3.oas.annotations.media.*
import io.swagger.v3.oas.annotations.responses.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.*
import org.springframework.validation.annotation.Validated
import ru.arch.smarthouse.telemetry.model.Telemetry
import ru.arch.smarthouse.telemetry.model.exception.NotFoundException
import ru.arch.smarthouse.telemetry.service.TelemetryService
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.UUID

import kotlin.collections.List

@RestController
@Validated
@RequestMapping("\${api.base-path:}")
class TelemetryController(
    private val telemetryService: TelemetryService,
) {

    @Operation(
        summary = "Получение телеметрии датчика",
        operationId = "v1TelemetryByDeviceDeviceIdGet",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "Телеметрия датчика", content = [Content(schema = Schema(implementation = DeviceTelemetry::class))]),
            ApiResponse(responseCode = "403", description = "У пользователя нет прав на просмотр показаний датчика"),
            ApiResponse(responseCode = "404", description = "Датчик не найден"),
            ApiResponse(responseCode = "500", description = "Ошибка сервера") ]
    )
    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v1/telemetry/by_device/{deviceId}"],
        produces = ["application/json"]
    )
    fun v1TelemetryByDeviceDeviceIdGet(@Parameter(description = "id пользователя", `in` = ParameterIn.HEADER, required = true) @RequestHeader(value = "userId", required = true) userId: kotlin.String,@Parameter(description = "Идентификатор датчика", required = true) @PathVariable("deviceId") deviceId: kotlin.String): ResponseEntity<DeviceTelemetry> {
        val telemetry = telemetryService.getByDeviceId(deviceId = UUID.fromString(deviceId)) ?: throw NotFoundException()
        return ResponseEntity.ok(telemetry.toDto())
    }

    @Operation(
        summary = "Получение телеметрии датчиков из заданной локации",
        operationId = "v1TelemetryByLocationLocationIdGet",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "Список телеметрий для всех датчиков из заданной локации", content = [Content(array = ArraySchema(schema = Schema(implementation = DeviceTelemetry::class)))]),
            ApiResponse(responseCode = "403", description = "У пользователя нет прав на просмотр датчиков локации"),
            ApiResponse(responseCode = "404", description = "Локация не найдена"),
            ApiResponse(responseCode = "500", description = "Ошибка сервера") ]
    )
    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v1/telemetry/by_location/{locationId}"],
        produces = ["application/json"]
    )
    fun v1TelemetryByLocationLocationIdGet(@Parameter(description = "id пользователя", `in` = ParameterIn.HEADER, required = true) @RequestHeader(value = "userId", required = true) userId: kotlin.String,@Parameter(description = "Идентификатор локации датчиков", required = true) @PathVariable("locationId") locationId: kotlin.String): ResponseEntity<List<DeviceTelemetry>> {
        val telemetries = telemetryService.getByLocationId(locationId = UUID.fromString(locationId))
        return ResponseEntity.ok(telemetries.map { it.toDto() })
    }

    private fun Telemetry.toDto() = DeviceTelemetry(
        deviceId = deviceId.toString(),
        status = status,
        createdAt = OffsetDateTime.ofInstant(createdAt, ZoneOffset.UTC),
        value = value,
        unit = unit,
    )
}
