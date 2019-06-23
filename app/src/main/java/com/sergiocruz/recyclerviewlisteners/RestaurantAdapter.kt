package com.sergiocruz.recyclerviewlisteners

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout
import androidx.core.view.children
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.sergiocruz.recyclerviewlisteners.MainActivity.Field
import com.sergiocruz.recyclerviewlisteners.MainActivity.Field.*
import com.sergiocruz.recyclerviewlisteners.MainActivity.SortOrder
import kotlinx.android.synthetic.main.item_restaurant.view.*
import java.util.*
import kotlin.Comparator
import kotlin.collections.LinkedHashMap


class RestaurantAdapter(
    private val inflater: LayoutInflater
) :
    RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    init {
        setHasStableIds(true)
    }

    private var data: List<Mesa>? = null

    fun swapData(data: List<Mesa>?) {
        this.data = data
        notifyDataSetChanged()
    }

    private fun getParams(): ViewGroup.MarginLayoutParams {
        val params: ViewGroup.MarginLayoutParams =
            ViewGroup.MarginLayoutParams(MATCH_PARENT, WRAP_CONTENT)
        params.topMargin = 4
        return params
    }

    override fun getItemCount() = data?.size ?: 0

    private lateinit var parent: ViewGroup

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        this.parent = parent
        val restaurantView = inflater.inflate(R.layout.item_restaurant, parent, false)
        //val restaurantView = inflater.inflate(R.layout.item_restaurant, null)
        return RestaurantViewHolder(restaurantView)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val mesa: Mesa? = data?.get(position)
        val productList: MutableList<Product> = mesa?.productList ?: return

        val linearLayout = holder.linearLayout
        drawRows(linearLayout, productList)
    }

    private fun drawRows(linearLayout: LinearLayout?, productList: MutableList<Product>?) {
        if (productList == null || linearLayout == null) return
        linearLayout.removeAllViews()
        for (product in productList) {
            val row: View = inflater.inflate(R.layout.item_product_mesa, null)
            val productHolder = bindProductRow(row, product)
            productHolder.rotateX()
            productHolder.layoutParams = getParams()
            linearLayout.addView(productHolder)
        }
    }


    private fun getViewsOrder(
        linearLayout: LinearLayout?,
        adapterPosition: Int
    ): LinkedHashMap<Product?, View?> {
        val linkedViewsToPosition: LinkedHashMap<Product?, View?> =
            LinkedHashMap()

        sortViewsByY(linearLayout)
            ?.asIterable()
            ?.withIndex()
            ?.forEach { it: IndexedValue<Map.Entry<Float, View?>> ->
                val view: View? = it.value.value
                val product: Product? = data?.get(adapterPosition)?.productList?.get(it.index)
                linkedViewsToPosition[product] = view
            }

        return linkedViewsToPosition
    }

    private fun sortViewsByY(linearLayout: LinearLayout?): SortedMap<Float, View?>? {
        val viewY: LinkedHashMap<Float, View?>? = LinkedHashMap()
        linearLayout?.children?.forEach { childView ->
            viewY?.set(childView.y, childView)
        }
        val viewYSorted: SortedMap<Float, View?>? =
            viewY?.toSortedMap(Comparator { f1, f2 -> compareValues(f1, f2) })
        return viewYSorted
    }


    private fun getViewAtIndexByY(linearLayout: LinearLayout?, index: Int): View? {
        sortViewsByY(linearLayout)
            ?.asIterable()
            ?.withIndex()
            ?.forEach { it: IndexedValue<Map.Entry<Float, View?>> ->
                if (it.index == index)
                    return it.value.value
            }
        return null
    }

    private fun sortThisByField(linearLayout: LinearLayout?, adapterPosition: Int, field: Field) {
        if (isAnimating) return
        val previousViewsOrders: LinkedHashMap<Product?, View?> =
            getViewsOrder(linearLayout, adapterPosition)

        val mesa = data?.get(adapterPosition)
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
        animateViewsToNewOrder(linearLayout, mesa?.productList, previousViewsOrders)
    }

    private fun animateViewsToNewOrder(
        linearLayout: LinearLayout?,
        newProductOrder: MutableList<Product>?,
        previousViewsOrders: LinkedHashMap<Product?, View?>
    ) {
        val animationList = mutableListOf<Animator>()
        newProductOrder?.withIndex()?.forEach { (newIndex: Int, product: Product) ->
            val previousView: View? = previousViewsOrders[product]
            val newView = getViewAtIndexByY(linearLayout, newIndex)

            val animY = ObjectAnimator.ofFloat(previousView, "y", newView?.y ?: 0f)
            val rotateX = ObjectAnimator.ofFloat(previousView, "rotationX", -280f, -360f)
            val alpha = ObjectAnimator.ofFloat(previousView, "alpha", 0.75f, 1f)
            val direction = if (previousView?.y ?: 0f > newView?.y ?: 0f) 1 else -1
            val animX = ObjectAnimator.ofFloat(
                previousView, "x",
                0f,
                20f * direction,
                0f
            )

            val animScaleX = ObjectAnimator.ofFloat(previousView, "scaleX", 1f, 2f, 1f)
            val animScaleY = ObjectAnimator.ofFloat(previousView, "scaleY", 1f, 2f, 1f)
            val animElevation = ObjectAnimator.ofFloat(previousView, "elevation", 1f, 20f, 1f)

//            animationList.add(animScaleX)
//            animationList.add(animScaleY)
//            animationList.add(animElevation)
            animationList.add(animY)
            animationList.add(rotateX)
            animationList.add(alpha)
        }

        AnimatorSet().apply {
            duration = 500
            playTogether(animationList)
            interpolator = AccelerateDecelerateInterpolator()
            addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {
                    isAnimating = true
                }

                override fun onAnimationEnd(animation: Animator?) {
                    isAnimating = false
                    //realignHeihgts(linearLayout)
                }

                override fun onAnimationCancel(animation: Animator?) {
                    isAnimating = false
                }

                override fun onAnimationStart(animation: Animator?) {
                    isAnimating = true
                }
            })
            start()
        }


    }

    private fun realignHeihgts(linearLayout: LinearLayout?) {
        linearLayout?.children?.forEachIndexed { index, view ->
            if (index == 0) return@forEachIndexed
            val previouws = linearLayout.get(index - 1)
            view.top = previouws.top + previouws.height + 2
        }
    }

    var isAnimating = false

    private fun comparator(field: Field, p1: Product?, p2: Product?): Int {
        return when (field) {
            Field1 -> compareValues(p1?.id, p2?.id)
            Field2 -> compareValues(p1?.name, p2?.name)
            Field3 -> compareValues(p1?.quantity, p2?.quantity)
            Field4 -> compareValues(p1?.price, p2?.price)
        }
    }

    inner class RestaurantViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val linearLayout: LinearLayout? = view.content

        init {

            view.theId.setOnClickListener {
                sortThisByField(linearLayout, adapterPosition, Field1)
            }

            view.nome.setOnClickListener {
                sortThisByField(linearLayout, adapterPosition, Field2)
            }

            view.quantidade.setOnClickListener {
                sortThisByField(linearLayout, adapterPosition, Field3)
            }

            view.price.setOnClickListener {
                sortThisByField(linearLayout, adapterPosition, Field4)
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

private fun View.rotateX() {
    val rotateX = ObjectAnimator.ofFloat(this, "rotationX", 200f, 360f)
    val alpha = ObjectAnimator.ofFloat(this, "alpha", 0.4f, 1f)

    AnimatorSet().apply {
        duration = 300
        interpolator = AccelerateDecelerateInterpolator()
//        interpolator = AnticipateOvershootInterpolator()
        playTogether(rotateX, alpha)
        start()
    }
}

