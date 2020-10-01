package com.shivam.habittrakker.Repositry


import com.shivam.habittrakker.database.Habit
import com.shivam.habittrakker.database.HabitDatabase

class HabitRepository(val db: HabitDatabase) {
    suspend fun insertHabit(habit: Habit)=db.getDao().insertHabit(habit)

    suspend fun deleteHabit(habit: Habit)=db.getDao().deleteHabit(habit)

    suspend fun updateHabit(habit: Habit)=db.getDao().updateHabit(habit)

    fun getAllHabit()=db.getDao().getAllHabit()

   suspend fun deleteAll()=db.getDao().deleteAll()


}