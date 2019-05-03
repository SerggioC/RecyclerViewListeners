package com.sergiocruz.recyclerviewlisteners

import android.content.Context
import android.graphics.drawable.Animatable2
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.orhanobut.logger.Logger
import com.sergiocruz.recyclerviewlisteners.MainActivity.Field
import com.sergiocruz.recyclerviewlisteners.MainActivity.SortOrder
import com.sergiocruz.recyclerviewlisteners.PersonAdapter.RecyclerViewClickListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), RecyclerViewClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(this)
        //recycler.layoutManager = StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)


        val thisFun: (View?, Int?) -> Boolean = { view: View?, position: Int? ->
            Logger.d("debug $application")
            Logger.e("error $view")
            Logger.w("warning $position")
            Logger.v("verbose")
            Logger.i("information")
            Logger.wtf("What a Terrible Failure")
            position == 1
        }

        val myval: IntProgression = 1..2

        val someSet: Set<String> = setOf<String>("q", "a", "a")

        val numbers: Array<Int> = arrayOf(1, 2, 3, 4, 5)

        val s: Boolean = numbers.all { it % 2 == 0 }

//        recycler.adapter = PersonAdapter(data, this, thisFun, { true })


        val inflater: LayoutInflater =
            getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        var restaurantAdapter: RestaurantAdapter? = null
        val sorter: (view: View?, position: Int?) -> Unit = { view: View?, position: Int? ->
            position?.let {
                val mesa: Mesa = restaurantData[position]
                val productsOfMesaX: MutableList<Product> = mesa.productList

//                val sortOrder = mesa.sortOrder
//                when (sortOrder) {
//                    ASCENDING -> {
//                        productsOfMesaX.sortWith(Comparator { o1, o2 ->
//                            compareValues(o2?.price, o1?.price)
//                        })
//                        mesa.sortOrder = DESCENDING
//                    }
//                    DESCENDING, ORIGINAL -> {
//                        productsOfMesaX.sortWith(Comparator { o1, o2 ->
//                            compareValues(o1?.price, o2?.price)
//                        })
//                        mesa.sortOrder = ASCENDING
//                    }
//                }
//                restaurantAdapter?.notifyItemChanged(position)
            }
        }

        restaurantAdapter = RestaurantAdapter(restaurantData, sorter, inflater)
        recycler.adapter = restaurantAdapter
        recycler.setHasFixedSize(false)

        button3.setOnClickListener {
            ProductsDialog().show(supportFragmentManager, "cenas")
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            animateAVD()
        }

    }
    fun showDialog() {
        val fragmentManager = supportFragmentManager
        val newFragment = ProductsDialog()

        val mIsLargeLayout = false
        if (mIsLargeLayout) {
            // The device is using a large layout, so show the fragment as a dialog
            newFragment.show(fragmentManager, "dialog")
        } else {
            // The device is smaller, so show the fragment fullscreen
            val transaction = fragmentManager.beginTransaction()
            // For a little polish, specify a transition animation
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            // To make it fullscreen, use the 'content' root view as the container
            // for the fragment, which is always the root view for the activity
            transaction.add(android.R.id.content, newFragment)
                .addToBackStack(null).commit()
        }
    }

    sealed class SortOrder {
        object ASCENDING : SortOrder()
        object DESCENDING : SortOrder()
        object ORIGINAL : SortOrder()
    }

    sealed class Field {
        object Field1 : Field()
        object Field2 : Field()
        object Field3 : Field()
        object Field4 : Field()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun animateAVD() {
        val avd = getDrawable(R.drawable.avd_no_connection) as AnimatedVectorDrawable
        noConnection?.setImageDrawable(avd)
        avd.registerAnimationCallback(
            object : Animatable2.AnimationCallback() {
                override fun onAnimationEnd(drawable: Drawable?) {
                    super.onAnimationEnd(drawable)
                    noConnection.visibility = GONE
                }
            })
        avd.start()

        noConnection?.setOnClickListener {
            avd.start()
        }
    }

    override fun onClickRecycler(view: View?, position: Int) {
    }

    companion object {
        val restaurantData: List<Mesa> = listOf(
            Mesa(
                0, "Mesa do canto", mutableListOf(
                    Product(0, "Batatas", 2, 1.5f),
                    Product(1, "Ananás", 4, 2.5f),
                    Product(2, "Bitoque de vaca", 6, 8.5f),
                    Product(3, "Feijoada de Chocos", 7, 7.5f),
                    Product(4, "Bolo de Beterraba", 8, 1.5f),
                    Product(5, "Cupcakes de Android", 5, 1.25f),
                    Product(6, "Gingerbread", 14, 1.5f),
                    Product(7, "Froyos", 7, 0.5f),
                    Product(8, "Mel com Café", 2, 1.5f),
                    Product(9, "Ice Cream", 5, 15f),
                    Product(10, "Jelly Beans", 20, .15f)
                )
            ),
            Mesa(
                0, "Mesa do Meio 1", mutableListOf(
                    Product(0, "Mel com Café", 2, 1.5f),
                    Product(1, "Batatas", 2, 1.6f),
                    Product(2, "Ananás", 4, 2.5f),
                    Product(3, "Bitoque de vaca", 2, 8.5f),
                    Product(4, "Feijoada de Chocos", 2, 7.5f),
                    Product(5, "Froyos", 7, 0.5f),
                    Product(6, "Ice Cream", 5, 15f),
                    Product(7, "Cupcakes de Android", 5, 1.25f),
                    Product(8, "Jelly Beans", 20, .15f),
                    Product(9, "Bolo de Beterraba", 2, 1.7f),
                    Product(10, "Gingerbread", 14, 1.8f)
                )
            ),
            Mesa(
                0, "Mesa da entrada", mutableListOf(
                    Product(0, "Feijoada de Chocos", 2, 7.5f),
                    Product(1, "Bitoque de vaca", 2, 8.5f),
                    Product(2, "Batatas", 2, 1.5f),
                    Product(3, "Ananás", 4, 2.5f),
                    Product(4, "Froyos", 7, 0.5f),
                    Product(5, "Mel com Café", 2, 1.5f),
                    Product(6, "Ice Cream", 5, 15f),
                    Product(7, "Cupcakes de Android", 5, 1.25f),
                    Product(8, "Jelly Beans", 20, .15f),
                    Product(9, "Bolo de Beterraba", 2, 1.5f),
                    Product(10, "Gingerbread", 14, 1.5f),
                    Product(11, "Lagosta estrelada", 12, 21.5f)
                )
            ),

            Mesa(
                0, "Mesa da entrada", mutableListOf(
                    Product(0, "Feijoada de Chocos", 2, 7.5f),
                    Product(1, "Bitoque de vaca", 2, 8.5f),
                    Product(2, "Batatas", 2, 1.5f),
                    Product(3, "Ananás", 4, 2.5f),
                    Product(4, "Froyos", 7, 0.5f),
                    Product(5, "Mel com Café", 2, 1.5f),
                    Product(6, "Ice Cream", 5, 15f),
                    Product(7, "Cupcakes de Android", 5, 1.25f),
                    Product(8, "Jelly Beans", 20, .15f),
                    Product(9, "Bolo de Beterraba", 2, 1.5f),
                    Product(10, "Gingerbread", 14, 1.5f),
                    Product(11, "Lagosta estrelada", 12, 21.5f)
                )
            ),

            Mesa(
                0, "Mesa da entrada", mutableListOf(
                    Product(0, "Feijoada de Chocos", 2, 7.5f),
                    Product(1, "Bitoque de vaca", 2, 8.5f),
                    Product(2, "Batatas", 2, 1.5f),
                    Product(3, "Ananás", 4, 2.5f),
                    Product(4, "Froyos", 7, 0.5f),
                    Product(5, "Mel com Café", 2, 1.5f),
                    Product(6, "Ice Cream", 5, 15f),
                    Product(7, "Cupcakes de Android", 5, 1.25f),
                    Product(8, "Jelly Beans", 20, .15f),
                    Product(9, "Bolo de Beterraba", 2, 1.5f),
                    Product(10, "Gingerbread", 14, 1.5f),
                    Product(11, "Lagosta estrelada", 12, 21.5f)
                )
            ),

            Mesa(
                0, "Mesa da entrada", mutableListOf(
                    Product(0, "Feijoada de Chocos", 2, 7.5f),
                    Product(1, "Bitoque de vaca", 2, 8.5f),
                    Product(2, "Batatas", 2, 1.5f),
                    Product(3, "Ananás", 4, 2.5f),
                    Product(4, "Froyos", 7, 0.5f),
                    Product(5, "Mel com Café", 2, 1.5f),
                    Product(6, "Ice Cream", 5, 15f),
                    Product(7, "Cupcakes de Android", 5, 1.25f),
                    Product(8, "Jelly Beans", 20, .15f),
                    Product(9, "Bolo de Beterraba", 2, 1.5f),
                    Product(10, "Gingerbread", 14, 1.5f),
                    Product(11, "Lagosta estrelada", 12, 21.5f)
                )
            ),

            Mesa(
                0, "Mesa da entrada", mutableListOf(
                    Product(0, "Feijoada de Chocos", 2, 7.5f),
                    Product(1, "Bitoque de vaca", 2, 8.5f),
                    Product(2, "Batatas", 2, 1.5f),
                    Product(3, "Ananás", 4, 2.5f),
                    Product(4, "Froyos", 7, 0.5f),
                    Product(5, "Mel com Café", 2, 1.5f),
                    Product(6, "Ice Cream", 5, 15f),
                    Product(7, "Cupcakes de Android", 5, 1.25f),
                    Product(8, "Jelly Beans", 20, .15f),
                    Product(9, "Bolo de Beterraba", 2, 1.5f),
                    Product(10, "Gingerbread", 14, 1.5f),
                    Product(11, "Lagosta estrelada", 12, 21.5f)
                )
            ),

            Mesa(
                0, "Mesa da entrada", mutableListOf(
                    Product(0, "Feijoada de Chocos", 2, 7.5f),
                    Product(1, "Bitoque de vaca", 2, 8.5f),
                    Product(2, "Batatas", 2, 1.5f),
                    Product(3, "Ananás", 4, 2.5f),
                    Product(4, "Froyos", 7, 0.5f),
                    Product(5, "Mel com Café", 2, 1.5f),
                    Product(6, "Ice Cream", 5, 15f),
                    Product(7, "Cupcakes de Android", 5, 1.25f),
                    Product(8, "Jelly Beans", 20, .15f),
                    Product(9, "Bolo de Beterraba", 2, 1.5f),
                    Product(10, "Gingerbread", 14, 1.5f),
                    Product(11, "Lagosta estrelada", 12, 21.5f)
                )
            )

        )


    }

    val data: Array<Person> = arrayOf(
        Person("1", "Elsie", "eponten0@jimdo,com"),
        Person("2", "Annalise", "averty1@jimdo,com"),
        Person("3", "Shaughn", "shecks2@ucla,edu"),
        Person("4", "Garwin", "gdami3@slideshare,net"),
        Person("5", "Rockwell", "dbaggiani8@jimdo,com"),
        Person("6", "Maurene", "mriceards5@webmd,com"),
        Person("1", "Elsie", "eponten0@jimdo,com"),
        Person("2", "Annalise", "averty1@jimdo,com"),
        Person("3", "Shaughn", "shecks2@ucla,edu"),
        Person("4", "Garwin", "gdami3@slideshare,net"),
        Person("5", "Rockwell", "dbaggiani8@jimdo,com"),
        Person("6", "Maurene", "mriceards5@webmd,com"),
        Person("1", "Elsie", "eponten0@jimdo,com"),
        Person("2", "Annalise", "averty1@jimdo,com"),
        Person("3", "Shaughn", "shecks2@ucla,edu"),
        Person("4", "Garwin", "gdami3@slideshare,net"),
        Person("5", "Rockwell", "dbaggiani8@jimdo,com"),
        Person("6", "Maurene", "mriceards5@webmd,com"),
        Person("1", "Elsie", "eponten0@jimdo,com"),
        Person("2", "Annalise", "averty1@jimdo,com"),
        Person("3", "Shaughn", "shecks2@ucla,edu"),
        Person("4", "Garwin", "gdami3@slideshare,net"),
        Person("5", "Rockwell", "dbaggiani8@jimdo,com"),
        Person("6", "Maurene", "mriceards5@webmd,com"),
        Person("1", "Elsie", "eponten0@jimdo,com"),
        Person("2", "Annalise", "averty1@jimdo,com"),
        Person("3", "Shaughn", "shecks2@ucla,edu"),
        Person("4", "Garwin", "gdami3@slideshare,net"),
        Person("5", "Rockwell", "dbaggiani8@jimdo,com"),
        Person("6", "Maurene", "mriceards5@webmd,com"),
        Person("1", "Elsie", "eponten0@jimdo,com"),
        Person("2", "Annalise", "averty1@jimdo,com"),
        Person("3", "Shaughn", "shecks2@ucla,edu"),
        Person("4", "Garwin", "gdami3@slideshare,net"),
        Person("5", "Rockwell", "dbaggiani8@jimdo,com"),
        Person("6", "Maurene", "mriceards5@webmd,com"),
        Person("1", "Elsie", "eponten0@jimdo,com"),
        Person("2", "Annalise", "averty1@jimdo,com"),
        Person("3", "Shaughn", "shecks2@ucla,edu"),
        Person("4", "Garwin", "gdami3@slideshare,net"),
        Person("5", "Rockwell", "dbaggiani8@jimdo,com"),
        Person("6", "Maurene", "mriceards5@webmd,com"),
        Person("1", "Elsie", "eponten0@jimdo,com"),
        Person("2", "Annalise", "averty1@jimdo,com"),
        Person("3", "Shaughn", "shecks2@ucla,edu"),
        Person("4", "Garwin", "gdami3@slideshare,net"),
        Person("5", "Rockwell", "dbaggiani8@jimdo,com"),
        Person("6", "Maurene", "mriceards5@webmd,com"),
        Person("1", "Elsie", "eponten0@jimdo,com"),
        Person("2", "Annalise", "averty1@jimdo,com"),
        Person("3", "Shaughn", "shecks2@ucla,edu"),
        Person("4", "Garwin", "gdami3@slideshare,net"),
        Person("5", "Rockwell", "dbaggiani8@jimdo,com"),
        Person("6", "Maurene", "mriceards5@webmd,com"),
        Person("1", "Elsie", "eponten0@jimdo,com"),
        Person("2", "Annalise", "averty1@jimdo,com"),
        Person("3", "Shaughn", "shecks2@ucla,edu"),
        Person("4", "Garwin", "gdami3@slideshare,net"),
        Person("5", "Rockwell", "dbaggiani8@jimdo,com"),
        Person("6", "Maurene", "mriceards5@webmd,com"),
        Person("1", "Elsie", "eponten0@jimdo,com"),
        Person("2", "Annalise", "averty1@jimdo,com"),
        Person("3", "Shaughn", "shecks2@ucla,edu"),
        Person("4", "Garwin", "gdami3@slideshare,net"),
        Person("5", "Rockwell", "dbaggiani8@jimdo,com"),
        Person("6", "Maurene", "mriceards5@webmd,com"),
        Person("1", "Elsie", "eponten0@jimdo,com"),
        Person("2", "Annalise", "averty1@jimdo,com"),
        Person("3", "Shaughn", "shecks2@ucla,edu"),
        Person("4", "Garwin", "gdami3@slideshare,net"),
        Person("5", "Rockwell", "dbaggiani8@jimdo,com"),
        Person("6", "Maurene", "mriceards5@webmd,com"),
        Person("7", "Shep", "sjohncey6@samsung,com")
    )


}


data class Mesa(
    val id: Int? = null,
    val name: String?,
    val productList: MutableList<Product>,
    var sortOrder: HashMap<HashMap<Int?, Field>, SortOrder> = hashMapOf()
) {
    fun getTotal(): Float {
        var total = 0.0f
        for (product in productList) {
            total += product.quantity * product.price
        }
        return total
    }
}

data class Product(val id: Int?, val name: String?, val quantity: Int = 0, val price: Float = 0.0f)

data class Person(var id: String? = null, var name: String? = null, var email: String? = null) {

    override fun toString(): String {
        return "Person id = $id, name = $name, email = $email"
    }
}


