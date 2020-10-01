package com.shivam.habittrakker.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shivam.habittrakker.Repositry.HabitRepository


@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: HabitRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HabitViewModel(repository) as T
    }
}
