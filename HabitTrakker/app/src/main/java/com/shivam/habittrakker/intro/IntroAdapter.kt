package com.shivam.habittrakker.intro

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shivam.habittrakker.R

class IntroAdapter(val list:List<IntroView>):RecyclerView.Adapter<IntroAdapter.IntroViewHolder>()
{

class IntroViewHolder(view: View):RecyclerView.ViewHolder(view)
{
    val iv_image_intro:ImageView=view.findViewById(R.id.iv_image_intro)
    val tv_description_intro:TextView=view.findViewById(R.id.tv_description_intro)
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.intro_item_page,parent,false)
    return IntroViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: IntroViewHolder, position: Int) {
        val cool=list[position]
    holder.iv_image_intro.setImageResource(cool.imageId)
    holder.tv_description_intro.text=cool.description
    }
}