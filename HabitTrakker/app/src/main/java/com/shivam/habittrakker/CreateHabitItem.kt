package com.shivam.habittrakker

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.shivam.habittrakker.Repositry.HabitRepository
import com.shivam.habittrakker.database.Habit
import com.shivam.habittrakker.database.HabitDatabase
import com.shivam.habittrakker.module.HabitViewModel
import com.shivam.habittrakker.module.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_create_habit_item.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class CreateHabitItem : Fragment(R.layout.fragment_create_habit_item),TimePickerDialog.OnTimeSetListener,DatePickerDialog.OnDateSetListener {

    var title=""
    var description=""
    var timeStamp=""
   var drawableSelected=0
   var day=0
    var month=0
    var year=0
    var minutes=0
    var hour=0
   var cleanTime=""
    var cleanDate=""
    lateinit var habitViewModel: HabitViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
val db=HabitDatabase(activity as Context)
        val habitRepository=HabitRepository(db)
val factory =ViewModelFactory(habitRepository)
        habitViewModel= ViewModelProvider(this, factory).get(HabitViewModel::class.java)
        btn_confirm.setOnClickListener {
            habitDb()


        }

drawableSelected()
pickTime()

    }

   fun habitDb(){
   title=et_habitTitle.text.toString()
   description=et_habitDescription.text.toString()
   timeStamp="$cleanDate $cleanTime"
if( !(title.isEmpty()||description.isEmpty()||timeStamp.isEmpty()||drawableSelected==0)) {
    val habit=Habit(title,description,timeStamp,drawableSelected)
    habitViewModel.insertHabit(habit)
    Toast.makeText(context, "Successfully add your habit!", Toast.LENGTH_LONG).show()
findNavController().navigate(R.id.action_createHabitItem_to_habitList)
}
   else {
    Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
}
}

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        cleanTime=Calculations.cleanTime(hourOfDay,minute)
        tv_timeSelected.text="TIME:$cleanTime"

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
            cleanDate=Calculations.cleanDate(dayOfMonth,month,year)
        tv_dateSelected.text="DATE:$cleanDate"

    }

    private fun drawableSelected(){


        iv_fastFoodSelected.setOnClickListener {
            iv_fastFoodSelected.isSelected = !iv_fastFoodSelected.isSelected
            drawableSelected = R.drawable.fast_food_s

            //de-select the other options when we pick an image
            iv_smokingSelected.isSelected = false
            iv_teaSelected.isSelected = false

        }

        iv_smokingSelected.setOnClickListener {
            iv_smokingSelected.isSelected = !iv_smokingSelected.isSelected
            drawableSelected = R.drawable.hot_cup

            //de-select the other options when we pick an image
            iv_fastFoodSelected.isSelected = false
            iv_teaSelected.isSelected = false
        }

        iv_teaSelected.setOnClickListener {
            iv_teaSelected.isSelected = !iv_teaSelected.isSelected
            drawableSelected = R.drawable.no_smoking_k

            //de-select the other options when we pick an image
            iv_fastFoodSelected.isSelected = false
            iv_smokingSelected.isSelected = false
        }

    }
fun pickTime(){
btn_pickDate.setOnClickListener {
    getDate()
    DatePickerDialog(requireContext(),this,year,month,day).show()
}
btn_pickTime.setOnClickListener {
    getTime()
    TimePickerDialog(context,this,hour,minutes,true).show()
}

}

    fun getDate(){
    val cal=Calendar.getInstance()
day=cal.get(Calendar.DAY_OF_MONTH)
month=cal.get(Calendar.MONTH)
year=cal.get(Calendar.YEAR)
}
fun getTime(){
    val cal=Calendar.getInstance()
hour=cal.get(Calendar.HOUR_OF_DAY)
minutes=cal.get(Calendar.MINUTE)
}


}
