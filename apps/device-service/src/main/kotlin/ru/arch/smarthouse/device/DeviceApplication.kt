package ru.arch.smarthouse.device

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DeviceApplication

fun main(args: Array<String>) {
	runApplication<DeviceApplication>(*args)
}
