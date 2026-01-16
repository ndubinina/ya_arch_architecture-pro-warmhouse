package ru.arch.smarthouse.device.api.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

/**
 *
 * @param deviceId идентификатор датчика
 * @param externalId идентификатор датчика во внешней системе (device external system)
 * @param name наименование датчика
 * @param type тип датчика
 * @param locationId идентификатор локации датчика
 */
data class DeviceResponse(

    @Schema(example = "null", required = true, description = "идентификатор датчика")
    @get:JsonProperty("deviceId", required = true) val deviceId: String,

    @Schema(example = "null", required = true, description = "идентификатор датчика во внешней системе (device external system)")
    @get:JsonProperty("externalId", required = true) val externalId: String,

    @Schema(example = "null", required = true, description = "наименование датчика")
    @get:JsonProperty("name", required = true) val name: String?,

    @Schema(example = "null", required = true, description = "тип датчика")
    @get:JsonProperty("type", required = true) val type: String,

    @Schema(example = "null", description = "идентификатор локации датчика")
    @get:JsonProperty("locationId") val locationId: String? = null
) {

}

