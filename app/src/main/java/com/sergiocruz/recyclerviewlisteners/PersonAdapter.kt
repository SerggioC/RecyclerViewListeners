package com.sergiocruz.recyclerviewlisteners

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_item.view.*

class PersonAdapter constructor(
    private val data: Array<Person>?,
    private val listener: RecyclerViewClickListener,
    private val thisFun: ((view: View?, position: Int?) -> Boolean),
    private val anotherFun: ((position: Int?) -> Boolean)
) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    override fun getItemCount() = data?.size ?: 0

    private var viewHolderCount = 0

    @SuppressLint("SetTextI18n")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        val personViewHolder = PersonViewHolder(view)
        personViewHolder.theId?.text = "HC= $viewHolderCount"
        viewHolderCount++
        return personViewHolder
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = data?.get(position)

        holder.nome?.text = " ID=${person?.id} P-> $position AP-> ${holder.adapterPosition}"

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
            thisFun(view, adapterPosition)
        }

    }

    interface RecyclerViewClickListener {
        fun onClickRecycler(view: View?, position: Int)
    }

}