package com.sergiocruz.recyclerviewlisteners

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_item.view.*

class PersonAdapter constructor(private val data: Array<Person>?, private val listener: RecyclerViewClickListener): RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    override fun getItemCount() = data?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonAdapter.PersonViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        val personViewHolder = PersonViewHolder(view, listener)
        personViewHolder.adapterPosition
        return personViewHolder
    }

    override fun onBindViewHolder(holder: PersonAdapter.PersonViewHolder, position: Int) {
        val person = data?.get(position)
        holder.theId?.text = person?.id + " p-> $position hp -> ${holder.adapterPosition}" ?: "<Empty>"
        holder.nome?.text = person?.name ?:  "<Empty>"
        holder.email?.text = person?.email ?: "<Empty>"
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }

    inner class PersonViewHolder(view: View, private val aListener: RecyclerViewClickListener) :
        RecyclerView.ViewHolder(view), View.OnClickListener {

        val theId: TextView? = view.theId
        val nome: TextView? = view.nome
        val email: TextView? = view.email

        init {
            nome?.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            aListener.onClick(view, adapterPosition)
        }

    }

    interface RecyclerViewClickListener {
        fun onClick(view: View?, position: Int)
    }
}