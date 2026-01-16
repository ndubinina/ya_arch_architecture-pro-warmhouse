package ru.arch.smarthouse.telemetry.api.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

/**
 * 
 * @param deviceId идентификатор датчика
 * @param status статус датчика(active, inactive)
 * @param createdAt время измерения
 * @param deviceType тип датчика
 * @param &#x60;value&#x60; значение показателя
 * @param unit единица измерения показателя
 */
data class DeviceTelemetry(

    @Schema(example = "null", required = true, description = "идентификатор датчика")
    @get:JsonProperty("deviceId", required = true) val deviceId: kotlin.String,

    @Schema(example = "null", required = true, description = "статус датчика(active, inactive)")
    @get:JsonProperty("status", required = true) val status: kotlin.String,

    @Schema(example = "null", required = true, description = "время измерения")
    @get:JsonProperty("createdAt", required = true) val createdAt: java.time.OffsetDateTime,

    @Schema(example = "null", description = "тип датчика")
    @get:JsonProperty("deviceType") val deviceType: kotlin.String? = null,

    @Schema(example = "null", description = "значение показателя")
    @get:JsonProperty("value") val `value`: kotlin.String? = null,

    @Schema(example = "null", description = "единица измерения показателя")
    @get:JsonProperty("unit") val unit: kotlin.String? = null
) {

}

