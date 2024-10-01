package co.baco.baco.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import co.baco.baco.common.utils.Constants
import co.baco.baco.common.entities.RegisterItem
import co.baco.baco.common.utils.Converters
import co.baco.baco.common.utils.DECIMAL_FORMAT
import java.util.UUID

@Entity(tableName = "registers")
data class RegisterEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val value: Double,
    val type: Constants.RegisterType,
    val comment: String? = null,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP") val createdAt: Long? = 0,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP") val updatedAt: Long? = 0
)

fun RegisterEntity.toDomain(): RegisterItem {
    return RegisterItem(
        id = id,
        amount = DECIMAL_FORMAT.format(value),
        type = type,
        comment = comment,
        createdAt = Converters().fromTimestamp(createdAt),
        updatedAt = Converters().fromTimestamp(updatedAt)
    )
}

