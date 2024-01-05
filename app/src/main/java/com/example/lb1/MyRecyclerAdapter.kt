package com.example.lb1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerAdapter (private val students: List<StudTab>) : RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>(){
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.name_tv)
        val weight : TextView = itemView.findViewById(R.id.weight_tv)
        val height : TextView = itemView.findViewById(R.id.height_tv)
        val age : TextView = itemView.findViewById(R.id.age_tv)

        fun bindData (student: StudTab){
            name.text = student.name
            height.text = student.height.toString()
            weight.text = student.weight.toString()
            age.text = student.age.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cell_info, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int = students.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val student = students[position]
        holder.bindData(student)
    }
}