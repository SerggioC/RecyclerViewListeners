package com.sergiocruz.recyclerviewlisteners

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(this)

        recycler.adapter = PersonAdapter(data, object : PersonAdapter.RecyclerViewClickListener {
            override fun onClick(view: View?, position: Int) {
                Log.i("Sergio> ", "position: $position view: $view")
            }
        })
    }


    val data: Array<Person> = arrayOf(
        Person("1", "Elsie", "eponten0@jimdo,com"),
        Person("2", "Annalise", "averty1@jimdo,com"),
        Person("3", "Shaughn", "shecks2@ucla,edu"),
        Person("4", "Garwin", "gdami3@slideshare,net"),
        Person("5", "Rockwell", "dbaggiani8@jimdo,com"),
        Person("6", "Maurene", "mriceards5@webmd,com"),        Person("1", "Elsie", "eponten0@jimdo,com"),
        Person("2", "Annalise", "averty1@jimdo,com"),
        Person("3", "Shaughn", "shecks2@ucla,edu"),
        Person("4", "Garwin", "gdami3@slideshare,net"),
        Person("5", "Rockwell", "dbaggiani8@jimdo,com"),
        Person("6", "Maurene", "mriceards5@webmd,com"),        Person("1", "Elsie", "eponten0@jimdo,com"),
        Person("2", "Annalise", "averty1@jimdo,com"),
        Person("3", "Shaughn", "shecks2@ucla,edu"),
        Person("4", "Garwin", "gdami3@slideshare,net"),
        Person("5", "Rockwell", "dbaggiani8@jimdo,com"),
        Person("6", "Maurene", "mriceards5@webmd,com"),        Person("1", "Elsie", "eponten0@jimdo,com"),
        Person("2", "Annalise", "averty1@jimdo,com"),
        Person("3", "Shaughn", "shecks2@ucla,edu"),
        Person("4", "Garwin", "gdami3@slideshare,net"),
        Person("5", "Rockwell", "dbaggiani8@jimdo,com"),
        Person("6", "Maurene", "mriceards5@webmd,com"),        Person("1", "Elsie", "eponten0@jimdo,com"),
        Person("2", "Annalise", "averty1@jimdo,com"),
        Person("3", "Shaughn", "shecks2@ucla,edu"),
        Person("4", "Garwin", "gdami3@slideshare,net"),
        Person("5", "Rockwell", "dbaggiani8@jimdo,com"),
        Person("6", "Maurene", "mriceards5@webmd,com"),        Person("1", "Elsie", "eponten0@jimdo,com"),
        Person("2", "Annalise", "averty1@jimdo,com"),
        Person("3", "Shaughn", "shecks2@ucla,edu"),
        Person("4", "Garwin", "gdami3@slideshare,net"),
        Person("5", "Rockwell", "dbaggiani8@jimdo,com"),
        Person("6", "Maurene", "mriceards5@webmd,com"),        Person("1", "Elsie", "eponten0@jimdo,com"),
        Person("2", "Annalise", "averty1@jimdo,com"),
        Person("3", "Shaughn", "shecks2@ucla,edu"),
        Person("4", "Garwin", "gdami3@slideshare,net"),
        Person("5", "Rockwell", "dbaggiani8@jimdo,com"),
        Person("6", "Maurene", "mriceards5@webmd,com"),        Person("1", "Elsie", "eponten0@jimdo,com"),
        Person("2", "Annalise", "averty1@jimdo,com"),
        Person("3", "Shaughn", "shecks2@ucla,edu"),
        Person("4", "Garwin", "gdami3@slideshare,net"),
        Person("5", "Rockwell", "dbaggiani8@jimdo,com"),
        Person("6", "Maurene", "mriceards5@webmd,com"),        Person("1", "Elsie", "eponten0@jimdo,com"),
        Person("2", "Annalise", "averty1@jimdo,com"),
        Person("3", "Shaughn", "shecks2@ucla,edu"),
        Person("4", "Garwin", "gdami3@slideshare,net"),
        Person("5", "Rockwell", "dbaggiani8@jimdo,com"),
        Person("6", "Maurene", "mriceards5@webmd,com"),        Person("1", "Elsie", "eponten0@jimdo,com"),
        Person("2", "Annalise", "averty1@jimdo,com"),
        Person("3", "Shaughn", "shecks2@ucla,edu"),
        Person("4", "Garwin", "gdami3@slideshare,net"),
        Person("5", "Rockwell", "dbaggiani8@jimdo,com"),
        Person("6", "Maurene", "mriceards5@webmd,com"),        Person("1", "Elsie", "eponten0@jimdo,com"),
        Person("2", "Annalise", "averty1@jimdo,com"),
        Person("3", "Shaughn", "shecks2@ucla,edu"),
        Person("4", "Garwin", "gdami3@slideshare,net"),
        Person("5", "Rockwell", "dbaggiani8@jimdo,com"),
        Person("6", "Maurene", "mriceards5@webmd,com"),        Person("1", "Elsie", "eponten0@jimdo,com"),
        Person("2", "Annalise", "averty1@jimdo,com"),
        Person("3", "Shaughn", "shecks2@ucla,edu"),
        Person("4", "Garwin", "gdami3@slideshare,net"),
        Person("5", "Rockwell", "dbaggiani8@jimdo,com"),
        Person("6", "Maurene", "mriceards5@webmd,com"),
        Person("7", "Shep", "sjohncey6@samsung,com")
    )

}

data class Person(val id: String, val name: String, val email: String)

