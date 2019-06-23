package com.sergiocruz.recyclerviewlisteners

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_item.view.*

class PersonAdapter(
    private val layoutInflater: LayoutInflater
) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    private var data: Array<Person>? = null
    private var listener: RecyclerViewClickListener? = null

    fun swapData(people: Array<Person>?, listener: RecyclerViewClickListener?) {
        this.data = people
        this.listener = listener
        notifyDataSetChanged()
    }


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
            checkBox.isChecked = person?.checked ?: false
            theId.text = "ID=${person?.id}"
            nome.text = person?.name
            email.text = person?.email
        }

    }

    override fun getItemId(position: Int) = position.toLong()

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }

    @SuppressLint("ClickableViewAccessibility")
    inner class PersonViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val theId: TextView = view.theId
        val nome: TextView = view.quantidade
        val email: TextView = view.price
        val checkBox: CheckBox = view.checkBox

        init {

            view.setOnTouchListener { _, motionEvent ->
                if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                    Toast.makeText(view.context, "Touch $adapterPosition", Toast.LENGTH_SHORT)
                        .show()
                }
                false
            }
            checkBox.setOnTouchListener { _, motionEvent ->
                if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                    Toast.makeText(view.context, "Touch $adapterPosition", Toast.LENGTH_SHORT)
                        .show()
                }
                false
            }
            view.setOnClickListener {
                handleClick(view, adapterPosition)
            }
            checkBox.setOnClickListener {
                handleClick(view, adapterPosition)
            }
        }

    }

    private fun handleClick(viewHolder: View?, adapterPosition: Int) {

        data?.get(adapterPosition)?.checked = data?.get(adapterPosition)?.checked?.not() ?: false

        notifyItemChanged(adapterPosition)

        listener?.onClickRecycler(viewHolder, adapterPosition)

    }

    interface RecyclerViewClickListener {
        fun onClickRecycler(view: View?, position: Int)
    }

}