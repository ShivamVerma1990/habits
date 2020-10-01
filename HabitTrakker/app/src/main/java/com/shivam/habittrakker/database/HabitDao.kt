package com.shivam.habittrakker.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.shivam.habittrakker.database.Habit

@Dao
interface HabitDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)

    suspend fun insertHabit(habit: Habit)
@Delete
    suspend fun deleteHabit(habit: Habit)
    @Update
    suspend fun updateHabit(habit: Habit)

    @Query("SELECT*FROM habit_table ORDER BY id DESC")
    fun getAllHabit():LiveData<List<Habit>>
@Query("DELETE FROM habit_table")
suspend fun deleteAll()
}