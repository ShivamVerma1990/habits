package com.shivam.habittrakker

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.shivam.habittrakker.Repositry.HabitRepository
import com.shivam.habittrakker.database.Habit
import com.shivam.habittrakker.database.HabitDatabase
import com.shivam.habittrakker.module.HabitViewModel
import com.shivam.habittrakker.module.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_create_habit_item.*
import kotlinx.android.synthetic.main.fragment_update_habit_item.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class UpdateHabitItem : Fragment(R.layout.fragment_update_habit_item), TimePickerDialog.OnTimeSetListener,
    DatePickerDialog.OnDateSetListener {
    var title = ""
    var description = ""
    var timeStamp = ""
    var drawableSelected = 0
    var day = 0
    var month = 0
    var year = 0
    var minutes = 0
    var hour = 0
    var cleanTime = ""
    var cleanDate = ""
    lateinit var habitViewModel: HabitViewModel
    private val args by navArgs<UpdateHabitItemArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val db = HabitDatabase(activity as Context)
        val habitRepository = HabitRepository(db)
        val factory = ViewModelFactory(habitRepository)
        habitViewModel = ViewModelProvider(this, factory).get(HabitViewModel::class.java)
et_habitTitle_update.setText(args.selectedHabit.habit_title)
        et_habitDescription_update.setText(args.selectedHabit.habit_description)
        drawableSelected()
        pickTime()
      setHasOptionsMenu(true)
        btn_confirm_update.setOnClickListener {
            title=et_habitTitle_update.text.toString()
            description=et_habitDescription_update.text.toString()
            timeStamp="$cleanDate $cleanTime"
            if( !(title.isEmpty()||description.isEmpty()||timeStamp.isEmpty()||drawableSelected==0)) {
                val habit= Habit(title,description,timeStamp,drawableSelected)
                habitViewModel.updateHabit(habit)
                Toast.makeText(context, "Successfully update your habit!", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_updateHabitItem_to_habitList)
            }
            else {
                Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            }
        }

    }


    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        cleanTime = Calculations.cleanTime(hourOfDay, minute)
        tv_timeSelected_update.text = "TIME:$cleanTime"

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        cleanDate = Calculations.cleanDate(year, month, dayOfMonth)
        tv_dateSelected_update.text = "DATE:$cleanDate"

    }

    private fun drawableSelected() {


        iv_fastFoodSelected_update.setOnClickListener {
            iv_fastFoodSelected_update.isSelected = !iv_fastFoodSelected_update.isSelected
            drawableSelected = R.drawable.fast_food_s

            //de-select the other options when we pick an image
            iv_smokingSelected_update.isSelected = false
            iv_teaSelected_update.isSelected = false

        }

        iv_smokingSelected_update.setOnClickListener {
            iv_smokingSelected_update.isSelected = !iv_smokingSelected_update.isSelected
            drawableSelected = R.drawable.hot_cup

            //de-select the other options when we pick an image
            iv_fastFoodSelected_update.isSelected = false
            iv_teaSelected_update.isSelected = false
        }

        iv_teaSelected_update.setOnClickListener {
            iv_teaSelected_update.isSelected = !iv_teaSelected_update.isSelected
            drawableSelected = R.drawable.no_smoking_k

            //de-select the other options when we pick an image
            iv_fastFoodSelected_update.isSelected = false
            iv_smokingSelected_update.isSelected = false
        }

    }

    fun pickTime() {
        btn_pickDate_update.setOnClickListener {
            getDate()
            DatePickerDialog(requireContext(), this, year, month, day).show()
        }
        btn_pickTime_update.setOnClickListener {
            getTime()
            TimePickerDialog(context, this, hour, minutes, true).show()
        }

    }

    fun getDate() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }

    fun getTime() {
        val cal = Calendar.getInstance()
        hour = cal.get(Calendar.HOUR_OF_DAY)
        minutes = cal.get(Calendar.MINUTE)



    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_menu_delete,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when(item.itemId){
        R.id.delete->deleteUpdate(args.selectedHabit)
    }

        return super.onOptionsItemSelected(item)
    }
    private fun deleteUpdate(habit:Habit)
    {

        val alertDailog= AlertDialog.Builder(context)
            .setTitle("DELETE")
            .setMessage("ARE YOU SURE!!")
            .setPositiveButton("Ok"){_,_->
                habitViewModel.deleteHabit(habit)
                findNavController().navigate(R.id.action_updateHabitItem_to_habitList)
            }
            .setNegativeButton("CANCEL"){_,_->

            }.create().show()





    }


    }
