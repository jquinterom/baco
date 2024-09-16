package co.baco.baco.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import co.baco.baco.common.utils.Converters
import co.baco.baco.data.database.dao.RegisterDao
import co.baco.baco.data.database.entities.RegisterEntity

@Database(entities = [RegisterEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class BacoDatabase : RoomDatabase(){

    abstract fun getRegisterDao(): RegisterDao

}