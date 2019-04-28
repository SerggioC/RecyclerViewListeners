package com.sergiocruz.recyclerviewlisteners

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.sergiocruz.recyclerviewlisteners.MainActivity.*
import com.sergiocruz.recyclerviewlisteners.MainActivity.Field.*
import kotlinx.android.synthetic.main.item_restaurant.view.*


class RestaurantAdapter(
    private val data: List<Mesa>?,
    private val sorter: (view: View?, position: Int?) -> Unit,
    private val inflater: LayoutInflater
) :
    RecyclerView.Adapter<RestaurantAdapter.RestauranteViewHolder>() {

    init {
        setHasStableIds(true)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestauranteViewHolder {
        val restaurantView = inflater.inflate(R.layout.item_restaurant, parent, false)
        return RestauranteViewHolder(restaurantView)
    }

    override fun onBindViewHolder(holder: RestauranteViewHolder, position: Int) {
        val mesa: Mesa? = data?.get(position)
        val productList: MutableList<Product> = mesa?.productList ?: return

        val linearLayout = holder.linearLayout
        drawRows(linearLayout, productList)

    }

    private fun drawRows(linearLayout: LinearLayout?, productList: MutableList<Product>?) {
        if (productList == null) return

        linearLayout?.removeAllViews()

        for (product in productList) {
            val row: View = inflater.inflate(R.layout.item_product_mesa, null, false)
            val productHolder = bindProductRow(row, product)
            animateRow(productHolder)
            linearLayout?.addView(productHolder)
        }
    }

    private fun animateRow(view: View) {
        val rotateX = ObjectAnimator.ofFloat(view, "rotationX", 180f, 360f)
        rotateX.interpolator = AccelerateDecelerateInterpolator()

        val alpha = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)

        val animationSet = AnimatorSet()
        animationSet.duration = 400
        animationSet.playTogether(rotateX, alpha)
        animationSet.start()
    }


    private fun sortThisByField(
        position: Int,
        field: Field
    ): MutableList<Product>? {
        val mesa = data?.get(position)
        val productsOfMesaX = mesa?.productList
        val order: HashMap<HashMap<Int?, Field>, SortOrder>? = mesa?.sortOrder
        val sortOrder: SortOrder = order?.get(hashMapOf(mesa.id to field)) ?: SortOrder.ORIGINAL

        when (sortOrder) {
            SortOrder.ASCENDING -> {
                productsOfMesaX?.sortWith(Comparator { p1, p2 -> comparator(field, p2, p1) })
                mesa?.sortOrder?.set(hashMapOf(mesa.id to field), SortOrder.DESCENDING)
            }
            SortOrder.DESCENDING, SortOrder.ORIGINAL -> {
                productsOfMesaX?.sortWith(Comparator { p1, p2 -> comparator(field, p1, p2) })
                mesa?.sortOrder?.set(hashMapOf(mesa.id to field), SortOrder.ASCENDING)
            }
        }
        return mesa?.productList
    }

    private fun comparator(field: Field, p1: Product?, p2: Product?): Int {
        return when (field) {
            Field1 -> compareValues(p1?.id, p2?.id)
            Field2 -> compareValues(p1?.name, p2?.name)
            Field3 -> compareValues(p1?.quantity, p2?.quantity)
            Field4 -> compareValues(p1?.price, p2?.price)
        }
    }

    inner class RestauranteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val linearLayout: LinearLayout? = view.content
        init {
            view.theId.setOnClickListener {
                val productList = sortThisByField(adapterPosition, Field1)
                drawRows(linearLayout, productList)
            }

            view.nome.setOnClickListener {
                val productList = sortThisByField(adapterPosition, Field2)
                drawRows(linearLayout, productList)
            }

            view.quantidade.setOnClickListener {
                val productList = sortThisByField(adapterPosition, Field3)
                drawRows(linearLayout, productList)
            }

            view.price.setOnClickListener {
                val productList = sortThisByField(adapterPosition, Field4)
                drawRows(linearLayout, productList)
            }
        }
    }

    private fun bindProductRow(view: View, product: Product): View {
        return view.apply {
            theId?.text = product.id.toString()
            nome?.text = product.name
            quantidade?.text = product.quantity.toString()
            price?.text = product.price.toString()
        }
    }

    override fun getItemId(position: Int) = position.toLong()

    override fun getItemViewType(position: Int) = position

}

