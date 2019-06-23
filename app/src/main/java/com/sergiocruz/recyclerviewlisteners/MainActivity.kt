package com.sergiocruz.recyclerviewlisteners

import android.graphics.drawable.Animatable2
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.sergiocruz.recyclerviewlisteners.MainActivity.Field
import com.sergiocruz.recyclerviewlisteners.MainActivity.SortOrder
import com.sergiocruz.recyclerviewlisteners.PersonAdapter.RecyclerViewClickListener
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

annotation class BuedaFixe

class MainActivity : AppCompatActivity(), RecyclerViewClickListener {

    private val theInjectedPersonAdapter: PersonAdapter by inject()
    private val theInjectedRestaurantAdapter: RestaurantAdapter by inject()

    @BuedaFixe
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//
//        // or directly get any instance
//        val someAdapter : PersonAdapter = get()

        button.setOnClickListener {

            if (recycler.adapter is RestaurantAdapter) {
                recycler.adapter = theInjectedPersonAdapter
                theInjectedPersonAdapter.swapData(personData, this)
            } else {
                recycler.adapter = theInjectedRestaurantAdapter
                theInjectedRestaurantAdapter.swapData(restaurantData)
            }

        }

        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        recycler.adapter = theInjectedPersonAdapter
        theInjectedPersonAdapter.swapData(personData, this)

        //PersonAdapter(personData, this, layoutInflater)
        //recycler.adapter = RestaurantAdapter(restaurantData, layoutInflater)


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
        Snackbar.make(recycler, "snacking $position", Snackbar.LENGTH_SHORT).show()
    }

    companion object {

        val personData: Array<Person> = arrayOf(
            Person(false, "1", "Elsie", "eponten0@jimdo,com"),
            Person(false, "2", "Annalise", "averty1@jimdo,com"),
            Person(false, "3", "Shaughn", "shecks2@ucla,edu"),
            Person(false, "4", "Garwin", "gdami3@slideshare,net"),
            Person(false, "5", "Rockwell", "dbaggiani8@jimdo,com"),
            Person(false, "6", "Maurene", "mriceards5@webmd,com"),
            Person(false, "1", "Elsie", "eponten0@jimdo,com"),
            Person(false, "2", "Annalise", "averty1@jimdo,com"),
            Person(false, "3", "Shaughn", "shecks2@ucla,edu"),
            Person(false, "4", "Garwin", "gdami3@slideshare,net"),
            Person(false, "5", "Rockwell", "dbaggiani8@jimdo,com"),
            Person(false, "6", "Maurene", "mriceards5@webmd,com"),
            Person(false, "1", "Elsie", "eponten0@jimdo,com"),
            Person(false, "2", "Annalise", "averty1@jimdo,com"),
            Person(false, "3", "Shaughn", "shecks2@ucla,edu"),
            Person(false, "4", "Garwin", "gdami3@slideshare,net"),
            Person(false, "5", "Rockwell", "dbaggiani8@jimdo,com"),
            Person(false, "6", "Maurene", "mriceards5@webmd,com"),
            Person(false, "1", "Elsie", "eponten0@jimdo,com"),
            Person(false, "2", "Annalise", "averty1@jimdo,com"),
            Person(false, "3", "Shaughn", "shecks2@ucla,edu"),
            Person(false, "4", "Garwin", "gdami3@slideshare,net"),
            Person(false, "5", "Rockwell", "dbaggiani8@jimdo,com"),
            Person(false, "6", "Maurene", "mriceards5@webmd,com"),
            Person(false, "1", "Elsie", "eponten0@jimdo,com"),
            Person(false, "2", "Annalise", "averty1@jimdo,com"),
            Person(false, "3", "Shaughn", "shecks2@ucla,edu"),
            Person(false, "4", "Garwin", "gdami3@slideshare,net"),
            Person(false, "5", "Rockwell", "dbaggiani8@jimdo,com"),
            Person(false, "6", "Maurene", "mriceards5@webmd,com"),
            Person(false, "1", "Elsie", "eponten0@jimdo,com"),
            Person(false, "2", "Annalise", "averty1@jimdo,com"),
            Person(false, "3", "Shaughn", "shecks2@ucla,edu"),
            Person(false, "4", "Garwin", "gdami3@slideshare,net"),
            Person(false, "5", "Rockwell", "dbaggiani8@jimdo,com"),
            Person(false, "6", "Maurene", "mriceards5@webmd,com"),
            Person(false, "1", "Elsie", "eponten0@jimdo,com"),
            Person(false, "2", "Annalise", "averty1@jimdo,com"),
            Person(false, "3", "Shaughn", "shecks2@ucla,edu"),
            Person(false, "4", "Garwin", "gdami3@slideshare,net"),
            Person(false, "5", "Rockwell", "dbaggiani8@jimdo,com"),
            Person(false, "6", "Maurene", "mriceards5@webmd,com"),
            Person(false, "1", "Elsie", "eponten0@jimdo,com"),
            Person(false, "2", "Annalise", "averty1@jimdo,com"),
            Person(false, "3", "Shaughn", "shecks2@ucla,edu"),
            Person(false, "4", "Garwin", "gdami3@slideshare,net"),
            Person(false, "5", "Rockwell", "dbaggiani8@jimdo,com"),
            Person(false, "6", "Maurene", "mriceards5@webmd,com"),
            Person(false, "1", "Elsie", "eponten0@jimdo,com"),
            Person(false, "2", "Annalise", "averty1@jimdo,com"),
            Person(false, "3", "Shaughn", "shecks2@ucla,edu"),
            Person(false, "4", "Garwin", "gdami3@slideshare,net"),
            Person(false, "5", "Rockwell", "dbaggiani8@jimdo,com"),
            Person(false, "6", "Maurene", "mriceards5@webmd,com"),
            Person(false, "1", "Elsie", "eponten0@jimdo,com"),
            Person(false, "2", "Annalise", "averty1@jimdo,com"),
            Person(false, "3", "Shaughn", "shecks2@ucla,edu"),
            Person(false, "4", "Garwin", "gdami3@slideshare,net"),
            Person(false, "5", "Rockwell", "dbaggiani8@jimdo,com"),
            Person(false, "6", "Maurene", "mriceards5@webmd,com"),
            Person(false, "1", "Elsie", "eponten0@jimdo,com"),
            Person(false, "2", "Annalise", "averty1@jimdo,com"),
            Person(false, "3", "Shaughn", "shecks2@ucla,edu"),
            Person(false, "4", "Garwin", "gdami3@slideshare,net"),
            Person(false, "5", "Rockwell", "dbaggiani8@jimdo,com"),
            Person(false, "6", "Maurene", "mriceards5@webmd,com"),
            Person(false, "1", "Elsie", "eponten0@jimdo,com"),
            Person(false, "2", "Annalise", "averty1@jimdo,com"),
            Person(false, "3", "Shaughn", "shecks2@ucla,edu"),
            Person(false, "4", "Garwin", "gdami3@slideshare,net"),
            Person(false, "5", "Rockwell", "dbaggiani8@jimdo,com"),
            Person(false, "6", "Maurene", "mriceards5@webmd,com"),
            Person(false, "7", "Shep", "sjohncey6@samsung,com")
        )

        val restaurantData: List<Mesa> = listOf(
            Mesa(
                0, "Mesa do canto", mutableListOf(
                    Product(0, "Batatas", 2, 1.5f),
                    Product(1, "Ananás", 4, 2.5f),
                    Product(2, "Bitoque de vaca", 6, 8.5f),
                    Product(3, "Feijoada de chocolate com mentol dos himalaias", 7, 7.5f),
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
                    Product(4, "Feijoada de chocolate", 2, 7.5f),
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
                    Product(0, "Feijoada de chocolate", 2, 7.5f),
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
                    Product(0, "Feijoada de chocolate", 2, 7.5f),
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
                    Product(0, "Feijoada de chocolate", 2, 7.5f),
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
                    Product(0, "Feijoada de chocolate", 2, 7.5f),
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
                    Product(0, "Feijoada de chocolate", 2, 7.5f),
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
                    Product(0, "Feijoada de chocolate", 2, 7.5f),
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

data class Person(
    var checked: Boolean,
    var id: String? = null,
    var name: String? = null,
    var email: String? = null
) {

    override fun toString(): String {
        return "Person id = $id, name = $name, email = $email"
    }
}


