package com.example.fifthtry

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.resit.R
import com.example.sqlite.BPlaysViewModel
import com.example.sqlite.BestPlays
import com.example.sqlite.playDatabase
import kotlinx.android.synthetic.main.recycler_view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RecyclerView : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        poiListView.layoutManager = LinearLayoutManager(this)
        // Create the adapter here
        poiListView.adapter = MyAdapter()
        // val textView: TextView = findViewById(R.id.android_text) as TextView
        val texto: TextView = findViewById(R.id.search)
        val add: Button = findViewById(R.id.add)
        val db = playDatabase.getDatabase(application)

        // Obtain the ViewModel object
        val viewModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
                BPlaysViewModel::class.java
            )

        // Observe any changes in the LiveData returned by getAllHits()
        viewModel.getAllBPlays().observe(this, Observer {
            val adapter = poiListView.adapter as MyAdapter
            adapter.notifyDataSetChanged()
        })


        add.setOnClickListener {
            lifecycleScope.launch {
                var Bplay: BestPlays? = null
                var id: Long = 0
                var name = Name.text.toString()
                var date = Artist.text.toString()
                var time = Year.text.toString()
                val tempPlay= BestPlays(0, name, date, time)
                withContext(Dispatchers.IO) {
                    id = db.BestPlaysDao().insert(tempPlay)
                    //Mesage is not being used but would retrieve the info from the database
                }
                code.setText(id.toString())
+

                Bplay?.apply {
                        Name.setText(title)
                       // Artist.setText(artist)
                        //Year.setText(year)
                    }
            }
        }




        }

    }


