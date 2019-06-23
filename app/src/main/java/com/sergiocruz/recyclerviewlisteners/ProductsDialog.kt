package com.sergiocruz.recyclerviewlisteners

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.popup_layout.*
import android.util.TypedValue
import android.util.DisplayMetrics
import androidx.constraintlayout.widget.ConstraintSet.MATCH_CONSTRAINT_SPREAD
import org.koin.android.ext.android.inject
import org.koin.core.context.GlobalContext.get

class ProductsDialog : DialogFragment() {

    private lateinit var localInflater: LayoutInflater

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.localInflater = inflater
        return inflater.inflate(R.layout.popup_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val popupAdapter = inject<RestaurantAdapter>()
        popupRecycler.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        popupRecycler.adapter = popupAdapter.value

        okButton.setOnClickListener { dismiss() }
        cancelButton.setOnClickListener { dismiss() }

        popupRecycler.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {

                val dp = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300f, DisplayMetrics()).toInt()
                val params = popupRecycler.layoutParams
                params.width = dp
//                params.height = dp
                popupRecycler.layoutParams = params


                Log.i("Sergio> ", "popupRecycler.height: ${popupRecycler.width}, ${popupRecycler.height}")
                popupRecycler.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })

    }


}