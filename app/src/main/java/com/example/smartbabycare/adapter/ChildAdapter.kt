package com.example.smartbabycare.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.smartbabycare.R
import com.example.smartbabycare.ScheduleActivity
import com.example.smartbabycare.model.Child
import timber.log.Timber

class ChildAdapter(var children: ArrayList<Child>, val mContext: Context): RecyclerView.Adapter<ChildAdapter.ChildViewHolder>() {
    class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val childImage: ImageView = itemView.findViewById(R.id.childAvatar)
        val childNameTv: TextView = itemView.findViewById(R.id.childName)
        val childAgeTv: TextView = itemView.findViewById(R.id.childAge)
        val childCard: CardView = itemView.findViewById(R.id.childCard)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.baby_recycler, parent, false)
        return ChildViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val currentChild = children[position]
        holder.childNameTv.text = "Child Name: " + currentChild.childName
        holder.childAgeTv.text = "Child Age: " + currentChild.childAge + " Years"
        val gender: String? = currentChild.childGender
        if (gender.equals("Female")){

            holder.childImage.setImageResource(R.drawable.girl)
        }
        else if(gender.equals("female")){

            holder.childImage.setImageResource(R.drawable.girl)
        }
        else{

            holder.childImage.setImageResource(R.drawable.boy)
        }
        holder.childCard.setOnClickListener{


            val intent = Intent(mContext,ScheduleActivity::class.java)
            mContext.startActivity(intent)


            Timber.d("OnChild Click card")




        }
    }

    override fun getItemCount() = children.size
}