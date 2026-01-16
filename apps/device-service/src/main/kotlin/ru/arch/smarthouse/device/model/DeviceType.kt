package ru.arch.smarthouse.device.model

enum class DeviceType {
    Temperature;

    companion object {
        fun from(value: String): DeviceType {
            if (value.trim().equals(Temperature.name, ignoreCase = true)) {
                return Temperature
            }
            throw IllegalArgumentException("Unknown type: $value")
        }
    }
}