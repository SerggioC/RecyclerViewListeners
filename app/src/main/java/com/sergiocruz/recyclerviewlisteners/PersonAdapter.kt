package com.sergiocruz.recyclerviewlisteners

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_item.view.*

class PersonAdapter(
    private val data: Array<Person>?,
    private val listener: RecyclerViewClickListener,
    private val layoutInflater: LayoutInflater
) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    override fun getItemCount() = data?.size ?: 0

    @SuppressLint("SetTextI18n")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = layoutInflater.inflate(R.layout.row_item, parent, false)
        return PersonViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = data?.get(position)

        holder.apply {
            theId?.text = "ID=${person?.id}"
            nome?.text = person?.name
            email?.text = person?.email
        }

    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }

    inner class PersonViewHolder(view: View) :
        RecyclerView.ViewHolder(view), View.OnClickListener {

        val theId: TextView? = view.theId
        val nome: TextView? = view.quantidade
        val email: TextView? = view.price

        init {
            nome?.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            listener.onClickRecycler(view, adapterPosition)
        }

    }

    interface RecyclerViewClickListener {
        fun onClickRecycler(view: View?, position: Int)
    }

}