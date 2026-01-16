package ru.arch.smarthouse.tempo_mock.api.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

data class DeviceControlRequest(

    @Schema(example = "null", required = true, description = "значение показателя")
    @get:JsonProperty("value", required = true) val `value`: String,

    @Schema(example = "null", required = true, description = "статус датчика(active, inactive)")
    @get:JsonProperty("status", required = true) val status: String
) {

}