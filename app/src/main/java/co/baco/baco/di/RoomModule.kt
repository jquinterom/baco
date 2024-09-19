package co.baco.baco.di

import android.content.Context
import androidx.room.Room
import co.baco.baco.data.database.BacoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    private val bacoDataBaseName = "baco_database"

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): BacoDatabase =
        Room.databaseBuilder(context, BacoDatabase::class.java, bacoDataBaseName).build()

    @Singleton
    @Provides
    fun provideRegisterDao(db: BacoDatabase) = db.getRegisterDao()


}