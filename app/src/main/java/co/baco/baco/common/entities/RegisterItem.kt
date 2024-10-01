package co.baco.baco.common.entities

import co.baco.baco.common.utils.Constants
import co.baco.baco.data.database.entities.RegisterEntity
import java.util.Date
import java.util.UUID

data class RegisterItem(
    val id: String = UUID.randomUUID().toString(),
    val amount: String,
    val type: Constants.RegisterType,
    val comment: String? = null,
    val createdAt: Date? = Date(),
    val updatedAt: Date? = Date()
)

fun RegisterItem.toDataBase() = RegisterEntity(
    value = amount.toDouble(),
    type = type,
    comment = comment ?: "",
    createdAt = createdAt?.time,
    updatedAt = updatedAt?.time
)