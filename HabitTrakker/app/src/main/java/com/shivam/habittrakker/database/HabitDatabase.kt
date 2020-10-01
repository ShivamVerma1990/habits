package com.shivam.habittrakker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Habit::class],version = 1,exportSchema = false)
abstract class HabitDatabase:RoomDatabase() {

abstract fun getDao(): HabitDao


companion object{
    @Volatile
    private var instance: HabitDatabase?=null
val Lock=Any()
operator fun invoke(context: Context)= instance
    ?: synchronized(Lock){
    instance
        ?: buildDatabase(
            context
        ).also {
    instance =it
    }
}
private fun buildDatabase(context: Context)= Room.databaseBuilder(context,
    HabitDatabase::class.java,"res_db").build()

}

}