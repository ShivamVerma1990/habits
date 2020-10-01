package com.shivam.habittrakker.module

import androidx.lifecycle.ViewModel
import com.shivam.habittrakker.Repositry.HabitRepository
import com.shivam.habittrakker.database.Habit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HabitViewModel(private val repository: HabitRepository):ViewModel() {
     fun insertHabit(habit: Habit)= CoroutineScope(Dispatchers.Main).launch {
repository.insertHabit(habit)
     }
     fun deleteHabit(habit: Habit)=CoroutineScope(Dispatchers.Main).launch{
repository.deleteHabit(habit)
     }

     fun updateHabit(habit: Habit)=CoroutineScope(Dispatchers.Main).launch{
         repository.updateHabit(habit)
     }
    fun getAllHabit()=repository.getAllHabit()
    fun deleteAll()=CoroutineScope(Dispatchers.Main).launch{
        repository.deleteAll()
    }


}