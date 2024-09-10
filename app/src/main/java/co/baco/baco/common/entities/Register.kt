package co.baco.baco.common.entities

import java.util.Date
import java.util.UUID



data class Register (
    val id: String = UUID.randomUUID().toString(),
    val amount: Float,
    val type: Constants.RegisterType,
    val comment: String? = null,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)