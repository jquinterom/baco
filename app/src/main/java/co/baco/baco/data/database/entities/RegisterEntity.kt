package co.baco.baco.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import co.baco.baco.common.entities.Constants
import co.baco.baco.common.entities.RegisterItem
import java.util.Date
import java.util.UUID

@Entity(tableName = "registers")
data class RegisterEntity(
//    @PrimaryKey(autoGenerate = true) val id: Int,
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val value: Float,
    val type: Constants.RegisterType,
    val comment: String? = null,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP") val createdAt: Long? = 0,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP") val updatedAt: Long? = 0
)




fun RegisterEntity.toDomain() = RegisterItem(
    id = id,
    amount = value,
    type = type,
    comment = comment,
    createdAt = Date(),
    updatedAt = Date()
)
