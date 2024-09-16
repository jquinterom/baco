package co.baco.baco.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.baco.baco.data.database.entities.RegisterEntity

@Dao
interface RegisterDao {
    @Query("SELECT * FROM registers ORDER BY createdAt DESC")
    fun getAll(): List<RegisterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(register: RegisterEntity)
}
