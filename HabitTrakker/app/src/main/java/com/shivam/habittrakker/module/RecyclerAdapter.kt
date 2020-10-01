package com.shivam.habittrakker.module

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.shivam.habittrakker.HabitListDirections
import com.shivam.habittrakker.R
import com.shivam.habittrakker.database.Habit
import kotlinx.android.synthetic.main.habit_list_adapter.view.*
import kotlinx.coroutines.selects.select

class RecyclerAdapter(var habitList:List<Habit>): RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>(){
class RecyclerViewHolder(view: View):RecyclerView.ViewHolder(view){
    val  tv_item_title:TextView=view.findViewById(R.id.tv_item_title)
    val tv_timeElapsed:TextView=view.findViewById(R.id.tv_timeElapsed)
    val  tv_item_description:TextView=view.findViewById(R.id.tv_item_description)
    val  tv_item_createdTimeStamp:TextView=view.findViewById(R.id.tv_item_createdTimeStamp)
    val  iv_habit_icon:ImageView=view.findViewById(R.id.iv_habit_icon)
    val cv_cardView:CardView=view.findViewById(R.id.cv_cardView)

}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
val view=LayoutInflater.from(parent.context).inflate(R.layout.habit_list_adapter,parent,false)
     return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return habitList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val cool=habitList[position]
    holder.iv_habit_icon.setImageResource(cool.imageId)
    holder.tv_item_title.text=cool.habit_title
        holder.tv_item_description.text=cool.habit_description
    holder.tv_timeElapsed.text=Calculations.calculateTimeBetweenDates(cool.habit_startTime)
    holder.tv_item_createdTimeStamp.text="Since:${cool.habit_startTime}"
    holder.cv_cardView.setOnClickListener{

        val action=HabitListDirections.actionHabitListToUpdateHabitItem(cool)
    action.arguments
   Navigation.findNavController(it).navigate(action)
    }

    }
fun setDate( habit:List<Habit>){

    this.habitList=habit
  notifyDataSetChanged()
}
}