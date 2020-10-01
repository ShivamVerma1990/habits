package com.shivam.habittrakker

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.View.inflate
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.core.graphics.drawable.DrawableCompat.inflate
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shivam.habittrakker.Repositry.HabitRepository
import com.shivam.habittrakker.database.Habit
import com.shivam.habittrakker.database.HabitDatabase
import com.shivam.habittrakker.module.HabitViewModel
import com.shivam.habittrakker.module.RecyclerAdapter
import com.shivam.habittrakker.module.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_create_habit_item.*
import kotlinx.android.synthetic.main.fragment_habit_list.*
import java.util.zip.Inflater

/**
 * A simple [Fragment] subclass.
 */
class HabitList : Fragment(R.layout.fragment_habit_list) {
private lateinit var habitViewModel:HabitViewModel
    var habitList= listOf<Habit>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val db= HabitDatabase(activity as Context)
        val habitRepository= HabitRepository(db)
        val factory = ViewModelFactory(habitRepository)
        habitViewModel= ViewModelProvider(this, factory).get(HabitViewModel::class.java)
        rv_habits.layoutManager=LinearLayoutManager(activity)
       val adapter=RecyclerAdapter(habitList)
        rv_habits.adapter=adapter

       habitViewModel.getAllHabit().observe(viewLifecycleOwner, Observer {
          adapter.setDate(it)
   habitList=it
       if(it.isEmpty())
       {
           rv_habits.visibility=View.GONE

     tv_emptyView.visibility=View.VISIBLE
       }
       else
       {
           rv_habits.visibility=View.VISIBLE

           tv_emptyView.visibility=View.GONE

       }
       })
        setHasOptionsMenu(true)
//for refreshing your devise
     swipeToRefresh.setOnRefreshListener {
       adapter.setDate(habitList)
         swipeToRefresh.isRefreshing=false
     }



        fab_add.setOnClickListener{
            findNavController().navigate(R.id.action_habitList_to_createHabitItem)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
inflater.inflate(R.menu.nav_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.deleteAll-> {

val alertDailog=AlertDialog.Builder(context)
    .setTitle("DELETE")
    .setMessage("ARE YOU SURE!!")
    .setPositiveButton("Ok"){_,_->
        habitViewModel.deleteAll()
    }
    .setNegativeButton("CANCEL"){_,_->

    }.create().show()
            }
            }
        return super.onOptionsItemSelected(item)
    }
}
